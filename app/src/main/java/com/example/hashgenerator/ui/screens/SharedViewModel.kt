package com.example.hashgenerator.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.MessageDigest
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private var _textToHash = mutableStateOf("")
    val textToHash = _textToHash

    private var _result = mutableStateOf("")
    val result = _result

    private var _copiedTextHeight = mutableStateOf(0.dp)
    val copiedTextHeight = _copiedTextHeight

    private var _hashAlgorithm = mutableStateOf("SHA-256")
    val hashAlgorithm = _hashAlgorithm

    private var _currentScreen = mutableStateOf("GenerateContent")
    val currentScreen = _currentScreen

    private var _homeItemsVisibility = mutableStateOf(true)
    val homeItemsVisibility = _homeItemsVisibility

    private var _homeButtonEnabled = mutableStateOf(true)
    val homeButtonEnabled = _homeButtonEnabled

    fun changeHashAlgorithm(algorithm: String){
        _hashAlgorithm.value = algorithm
    }

    fun getHash() {
        val bytes: ByteArray =
            MessageDigest.getInstance(hashAlgorithm.value).digest(textToHash.value.toByteArray())
        _result.value = toHex(bytes)
    }

    private fun toHex(bytes: ByteArray): String {
        return bytes.joinToString(separator = "") { "%02x".format(it) }
    }

    fun changeFields(
        visible: Boolean,
        buttonEnabled: Boolean,
        screen: String,
    ) {
        changeHomeItemsVisibility(visible)
        changeCurrentScreen(screen)
        changeHomeButtonEnable(buttonEnabled)
    }

    fun setTextToTextToHash(text: String) {
        _textToHash.value = text
    }

    private fun changeCurrentScreen(screen: String) {
        _currentScreen.value = screen
    }

    private fun changeHomeItemsVisibility(visible: Boolean) {
        _homeItemsVisibility.value = visible
    }

    private fun changeHomeButtonEnable(buttonEnabled: Boolean) {
        _homeButtonEnabled.value = buttonEnabled
    }

    fun copyToClipBoard(result: String, localContext: Context) {
        val clipboardManager =
            localContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Encrypted Text", result)
        clipboardManager.setPrimaryClip(clipData)
    }

    fun showCopiedText() {
        viewModelScope.launch {
            _copiedTextHeight.value = 32.dp
            delay(2000)
            _copiedTextHeight.value = 0.dp
        }
    }
}