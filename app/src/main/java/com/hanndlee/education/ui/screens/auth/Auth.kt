package com.hanndlee.education.ui.screens.auth

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hanndlee.education.ui.components.TabLayout
import com.airbnb.lottie.compose.*
import com.hanndlee.education.R


@Composable
fun AuthAnimation(){
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.auth_lottie) // Replace with your animation file
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}










@Composable
fun Auth(navController: NavController) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 32.dp
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var selectedIndex by remember {
            mutableIntStateOf(0)
        }

        AuthAnimation()
        Spacer(modifier = Modifier.height(24.dp))
        TabLayout(
            selectedIndex = selectedIndex,
            items = listOf(
                "Login" to {
                    Login(
                        navController = navController,
                        sharedPreferences = sharedPreferences
                    )
                },
                "Register" to {
                    Register(
                        navController = navController,
                        sharedPreferences = sharedPreferences
                    )
                }
            ),
            onTabClick = {
                selectedIndex = it
            }
        )
    }
}