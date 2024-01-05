package com.hanndlee.education.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String ? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
) {

    Column {
        Text(text = label ?: "")
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier
            .shadow(3.dp, RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
        ){
            TextField(
                value = value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = onValueChange,
                placeholder = { Text(text = placeholder?: "") },
                visualTransformation = visualTransformation,
                trailingIcon = trailingIcon,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
        }
    }


}