package com.mukesh.contactmachinetask.presentation.ui.contactDetail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mukesh.contactmachinetask.common.DRAWABLE_ALIAS
import com.mukesh.contactmachinetask.common.ShowSnackBar
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto
import com.mukesh.contactmachinetask.presentation.ui.contactDetail.ContactDetailVM


@Composable
fun DetailUi(contactDto: ContactDto, contactDetailVM: ContactDetailVM) {

    val sendMessageState = contactDetailVM.sendMessageState.value

    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(10.dp)
            .scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {
        TextFieldWithHint(hint = "First Name", title = contactDto.firstName.orEmpty())
        TextFieldWithHint(hint = "Last Name", title = contactDto.lastName.orEmpty())
        TextFieldWithHint(hint = "Contact Number", title = "+${contactDto.countryCode} ${contactDto.phoneNumber.orEmpty()}")

        AnimatedVisibility(visible = sendMessageState.isLoading != true ) {
            ElevatedButton(
                onClick = { contactDetailVM.sendMessage(contactDto = contactDto) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Send Message")
                    Spacer(modifier = Modifier.padding(10.dp))
                    Icon(painter = painterResource(id = DRAWABLE_ALIAS.ic_send), contentDescription = "Send")
                }
            }
        }

        AnimatedVisibility(visible = sendMessageState.isLoading == true, Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.size(40.dp).fillMaxWidth().padding(10.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        sendMessageState.error?.let {
            ShowSnackBar(string = it.localizedMessage.orEmpty())
        }

    }
}
