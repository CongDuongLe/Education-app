package com.hanndlee.education.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hanndlee.education.ui.screens.auth.Auth
import com.hanndlee.education.ui.screens.auth.ForgetPassword
import com.hanndlee.education.ui.screens.detail.Detail
import com.hanndlee.education.ui.screens.home.Home

@Composable

fun RootNavigator() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)

    val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

    NavHost(navController = navController, startDestination = if (isLoggedIn) "home" else "auth") {
        composable("home") { Home(navController = navController) }
        composable("auth") { Auth(navController = navController) }
        composable("detail") { Detail(navController = navController) }
        composable("forgetPassword") { ForgetPassword(navController = navController) }
    }
}