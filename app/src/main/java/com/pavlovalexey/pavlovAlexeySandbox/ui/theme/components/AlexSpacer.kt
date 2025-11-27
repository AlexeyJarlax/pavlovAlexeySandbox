package com.pavlovalexey.pavlovAlexeySandbox.ui.theme.components

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HSpacer(size: Int = 16) {
    Spacer(Modifier.width(size.dp))
}

@Composable
fun VSpacer(size: Int = 16) {
    Spacer(Modifier.height(size.dp))
}