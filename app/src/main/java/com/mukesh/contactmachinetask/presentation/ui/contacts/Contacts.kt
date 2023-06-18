package com.mukesh.contactmachinetask.presentation.ui.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mukesh.contactmachinetask.common.STRING_ALIAS
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto
import com.mukesh.contactmachinetask.presentation.navController.Screens
import com.mukesh.contactmachinetask.presentation.ui.commonComponent.AppBarComponent
import com.mukesh.contactmachinetask.presentation.ui.commonComponent.ErrorComponent
import com.mukesh.contactmachinetask.presentation.ui.commonComponent.LoadingComponent
import com.mukesh.contactmachinetask.presentation.ui.contacts.components.ContactItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Contacts(
    navHostController: NavHostController,
    viewModel: ContactsVM = hiltViewModel()
) {
    val state = viewModel.allContactsState.value
    Scaffold(
        topBar = {
            AppBarComponent(
                navHostController = navHostController,
                title = stringResource(STRING_ALIAS.my_contacts),
                showBackButton = false
            )
        }
    ){ paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            state.data?.let {
                ShowUi(navHostController = navHostController, list = it)
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
fun ShowUi(navHostController: NavHostController, list: List<ContactDto>){
    LazyColumn(){
        items(items = list){ contactDto ->
            ContactItem(contactDto = contactDto){
                navHostController.navigate(Screens.ContactDetailScreen.route.plus("/${contactDto.id}"))
            }
        }
    }
}