package com.mukesh.contactmachinetask.presentation.ui.commonComponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LoadingComponent() {
    Box(
        contentAlignment = Alignment.Center
    ){
       CircularProgressIndicator(
           modifier = Modifier.size(80.dp)
       )
    }
}