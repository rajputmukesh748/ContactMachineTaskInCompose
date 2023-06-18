package com.mukesh.contactmachinetask.presentation.ui.messages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mukesh.contactmachinetask.common.STRING_ALIAS
import com.mukesh.contactmachinetask.data.remote.dto.MessagesDto
import com.mukesh.contactmachinetask.presentation.ui.commonComponent.AppBarComponent
import com.mukesh.contactmachinetask.presentation.ui.commonComponent.ErrorComponent
import com.mukesh.contactmachinetask.presentation.ui.commonComponent.LoadingComponent
import com.mukesh.contactmachinetask.presentation.ui.messages.components.MessageItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Messages(
    navHostController: NavHostController,
    viewModel : MessagesVM = hiltViewModel()
) {
    val state = viewModel.allMessagesState.value

    LaunchedEffect(key1 = Unit){
        viewModel.getAllMessages()
    }

    Scaffold(
        topBar = {
            AppBarComponent(
                navHostController = navHostController,
                title = stringResource(STRING_ALIAS.messages)
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            state.data?.let {
                ShowUi(list = it)
            } ?: run {
                ErrorComponent(string = stringResource(STRING_ALIAS.no_data_found))
            }

            if (state.isLoading == true){
                LoadingComponent()
            }

            state.error?.let {
                ErrorComponent(string = it.localizedMessage.orEmpty())
            }
        }
    }
}


@Composable
fun ShowUi(list: List<MessagesDto>){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(items = list){ messagesDto ->
            MessageItem(
                messagesDto = messagesDto
            )
        }
    }
}