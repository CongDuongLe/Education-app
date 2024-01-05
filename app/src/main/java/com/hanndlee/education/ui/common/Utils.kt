package com.hanndlee.education.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp


@Composable
fun maxHeight() = LocalDensity.current.run {
    LocalConfiguration.current.screenHeightDp.dp.toPx()
}