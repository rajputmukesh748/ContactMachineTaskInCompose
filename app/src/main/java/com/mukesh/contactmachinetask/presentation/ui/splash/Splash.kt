package com.mukesh.contactmachinetask.presentation.ui.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mukesh.contactmachinetask.common.DRAWABLE_ALIAS
import com.mukesh.contactmachinetask.common.readJsonFile
import com.mukesh.contactmachinetask.presentation.navController.Screens
import com.mukesh.contactmachinetask.presentation.ui.commonComponent.ErrorComponent
import com.mukesh.contactmachinetask.presentation.ui.commonComponent.LoadingComponent
import kotlinx.coroutines.delay

@Composable
fun Splash(
    navHostController: NavHostController,
    viewModel: SplashVM = hiltViewModel()
) {
    //Read Data
    LocalContext.current.also {
        LaunchedEffect(key1 = Unit){
            it.readJsonFile(fileName = "contactList.json").run {
                viewModel.saveAllContacts(this)
            }
        }
    }

    val state = viewModel.saveContactState.value

    //Handle State
    state.data?.let {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = DRAWABLE_ALIAS.ic_message), contentDescription = "SplashIcon", modifier = Modifier.size(80.dp))
        }
        LaunchedEffect(key1 = Unit){
            delay(2000)
            navHostController.navigate(Screens.ContactsScreen.route)
        }
    }

    if (state.isLoading == true){
        LoadingComponent()
    }

    state.error?.let {
        ErrorComponent(string = it.localizedMessage.orEmpty())
    }

}