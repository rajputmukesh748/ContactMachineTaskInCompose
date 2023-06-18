package com.mukesh.contactmachinetask.presentation.ui.contactDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextFieldWithHint(
    hint: String,
    title: String
){
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(top = 15.dp)
    ) {
        Text(hint, modifier = Modifier.fillMaxWidth(), fontSize = 14.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        Text(title, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp), fontSize = 16.sp, color = Color.DarkGray, fontWeight = FontWeight.Normal)
    }
}