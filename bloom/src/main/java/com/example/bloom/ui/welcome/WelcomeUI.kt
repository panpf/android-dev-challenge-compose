package com.example.bloom.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloom.R
import com.example.bloom.ui.theme.MyTheme

@Composable
fun WelcomeUI() {
    Box(
        Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.welcome_bg),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(),
        )
        Column(Modifier.fillMaxSize()) {
            Spacer(Modifier.size(80.dp))
            Image(
                painter = painterResource(R.drawable.welcome_illos),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.End)
                    .graphicsLayer(translationX = 100.dp.value)
            )
            Spacer(Modifier.size(48.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Beautiful home garden solutions",
                color = colorResource(R.color.subtitle1),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.size(40.dp))
            Button(
                onClick = {},
                colors = buttonColors(colorResource(R.color.buttonBg), colorResource(R.color.buttonText)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 16.dp, end=16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Create account")
            }
            Spacer(Modifier.size(8.dp))
            Button(
                onClick = {},
                colors = buttonColors(colorResource(R.color.buttonBg_noBg), colorResource(R.color.buttonText_noBg)),
                elevation = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 16.dp, end=16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Log in")
            }
        }
    }
}

@Preview
@Composable
fun WelcomeUIPreview() {
    MyTheme(darkTheme = false) {
        WelcomeUI()
    }
}

@Preview
@Composable
fun WelcomeUIPreviewDark() {
    MyTheme(darkTheme = true) {
        WelcomeUI()
    }
}