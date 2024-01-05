package com.hanndlee.education.ui.screens.auth

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.hanndlee.education.R
import com.hanndlee.education.ui.components.AppTextInput
import com.hanndlee.education.ui.components.LoginButton
import com.hanndlee.education.ui.theme.Inter

@Composable
fun ForgetPassword(
    navController: NavController,
) {

    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 32.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Header()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Dont worry, we will send you a link to reset your password",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = Inter
            )
        Spacer(modifier = Modifier.height(24.dp))
        AppTextInput(
            value = email,
            onValueChange = {
            email = it
            },
            placeholder = "Enter your email"
        )
        Spacer(modifier = Modifier.height(24.dp))
        LoginButton(
            text = "Send verify code to email",
            enable = email.isNotEmpty(),
            onClick = {
            navController.navigate("auth")
        })
    }

}



@Composable
fun Header(){
    val dotLottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.dont_worry) // Replace with your dotLottie file
    )
    val dotLottieProgress by animateLottieCompositionAsState(
        composition = dotLottieComposition,
        iterations = LottieConstants.IterateForever
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f),
    ) {
       Box(
           modifier = Modifier.fillMaxSize(),
           contentAlignment = Alignment.Center
       ) {
           LottieAnimation(
               composition = dotLottieComposition,
               progress = dotLottieProgress
           )
       }
    }
}