package com.mukesh.contactmachinetask.presentation.ui.commonComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mukesh.contactmachinetask.common.DRAWABLE_ALIAS

@Composable
fun ErrorComponent(
    string: String
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(painter = painterResource(id = DRAWABLE_ALIAS.ic_error), contentDescription = "Error")
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = string, modifier = Modifier.fillMaxWidth(), color = androidx.compose.ui.graphics.Color.Red, fontSize = 12.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
        }
    }
}