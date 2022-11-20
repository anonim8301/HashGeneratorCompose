package com.example.hashgenerator.ui.screens.result

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.hashgenerator.R
import com.example.hashgenerator.ui.theme.customBlue100
import com.example.hashgenerator.ui.theme.customBlue200
import com.example.hashgenerator.ui.theme.customBlue400
import com.example.hashgenerator.ui.theme.customWhite


@Composable
fun ResultContent(
    result: String,
    onCopyClicked: () -> Unit,
    copiedTextHeight: Dp,
) {

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(customBlue400))
    {
        val (button, resultText, copiedText) = createRefs()
        val height by animateDpAsState(
            targetValue = copiedTextHeight,
            animationSpec = tween(500)
        )

        Box(modifier = Modifier
            .constrainAs(copiedText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .height(height)
            .fillMaxWidth()
            .background(customBlue200),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier,
                text = "Copied!",
                color = customWhite,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
        }

        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .constrainAs(resultText) {
                    top.linkTo(copiedText.top)
                    bottom.linkTo((button.top))
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = result,
            style = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = customBlue100
            ),
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                }
                .height(80.dp)
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                onCopyClicked()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = customBlue200),
        ) {
            Image(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = R.drawable.ic_copy),
                contentDescription = ""
            )
            Text(
                modifier = Modifier,
                color = customWhite,
                fontSize = 16.sp,
                text = "COPY"
            )
        }
    }
}
