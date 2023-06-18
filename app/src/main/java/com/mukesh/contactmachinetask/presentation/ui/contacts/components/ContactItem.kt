package com.mukesh.contactmachinetask.presentation.ui.contacts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto

@Composable
fun ContactItem(contactDto: ContactDto, onClick: ContactDto.() -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp).clickable {
                onClick.invoke(contactDto)
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Text(text = "${contactDto.firstName.orEmpty()} ${contactDto.lastName.orEmpty()}", modifier = Modifier.fillMaxWidth(), color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "+${contactDto.countryCode} ${contactDto.phoneNumber.orEmpty()}", modifier = Modifier.fillMaxWidth(), color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Normal)
        }
    }
}