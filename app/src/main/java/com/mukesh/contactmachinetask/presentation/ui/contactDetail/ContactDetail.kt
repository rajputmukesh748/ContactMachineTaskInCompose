package com.mukesh.contactmachinetask.presentation.ui.contactDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mukesh.contactmachinetask.common.STRING_ALIAS
import com.mukesh.contactmachinetask.presentation.ui.commonComponent.AppBarComponent
import com.mukesh.contactmachinetask.presentation.ui.commonComponent.ErrorComponent
import com.mukesh.contactmachinetask.presentation.ui.commonComponent.LoadingComponent
import com.mukesh.contactmachinetask.presentation.ui.contactDetail.components.DetailUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetail(
    navHostController: NavHostController,
    viewModel: ContactDetailVM = hiltViewModel()
) {
    val state = viewModel.contactDetailState.value
    Scaffold(
        topBar = {
            AppBarComponent(
                navHostController = navHostController,
                title = stringResource(STRING_ALIAS.contact_detail),
                showBackButton = true
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(contentPadding).padding(top = 20.dp, start = 10.dp, end = 10.dp)
        ) {
            if (state.isLoading == true){
                LoadingComponent()
            }
            state.error?.let {
                ErrorComponent(string = it.localizedMessage.orEmpty())
            }
            state.data?.let {
                DetailUi(contactDto = it, contactDetailVM = viewModel)
            }
        }
    }
}
