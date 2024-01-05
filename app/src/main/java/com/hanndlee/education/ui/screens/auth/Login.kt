package com.hanndlee.education.ui.screens.auth

import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hanndlee.education.R
import com.hanndlee.education.ui.components.AppTextInput
import com.hanndlee.education.ui.components.LoginButton

@Composable
fun Login(
    navController: NavController,
    sharedPreferences: SharedPreferences
) {
    var isChecked by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showPassWord by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Spacer(modifier = Modifier.height(12.dp))

        AppTextInput(
            value = email,
            onValueChange = {
            email = it
            },
            label = "Email", placeholder = "Enter your email")
        Spacer(modifier = Modifier.height(24.dp))
        AppTextInput(
            value = password,
            onValueChange = {
            password = it
            },
            visualTransformation = if (showPassWord) VisualTransformation.None else PasswordVisualTransformation(),
            label = "Password", placeholder = "Enter your password",
            trailingIcon = {
                IconButton(onClick = {
                    showPassWord = !showPassWord
                }) {
                   Icon(
                       painter = painterResource(id = if (showPassWord) R.drawable.ic_eye else R.drawable.ic_eye_slash),
                       contentDescription = "Password Visibility",
                       modifier = Modifier.size(24.dp)
                   )
                }
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = {
                    isChecked = !isChecked
                }) {
                    Icon(
                        painter = painterResource(id = if (isChecked) R.drawable.ic_check else R.drawable.ic_uncheck),
                        contentDescription = "Remember me",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Remember me")
            }
            Text(text = "Forgot password?", modifier = Modifier.clickable {
                navController.navigate("forgetPassword")
            })
        }
        Spacer(modifier = Modifier.height(24.dp))
        LoginButton(
            text = "Login",
            enable = email.isNotEmpty() && password.isNotEmpty(),
            onClick = {
                sharedPreferences.edit().apply{
                    putBoolean("isLoggedIn", true)
                    putString("email", email)
                }.apply()
                navController.navigate("home")
            })
        Spacer(modifier = Modifier.height(24.dp))
        LoginButton(text = "Login with google",
            icon = R.drawable.ic_google,
            enable = true,
            onClick = {
                    sharedPreferences.edit().apply {
                        putBoolean("isLoggedIn", true)
                        putString("email", email)
                    }.apply()
                    navController.navigate("home"){
                        popUpTo(0)
                    }
            })


    }


}