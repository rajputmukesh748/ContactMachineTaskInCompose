package com.mukesh.contactmachinetask.presentation.navController

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mukesh.contactmachinetask.presentation.ui.contactDetail.ContactDetail
import com.mukesh.contactmachinetask.presentation.ui.contacts.Contacts
import com.mukesh.contactmachinetask.presentation.ui.messages.Messages
import com.mukesh.contactmachinetask.presentation.ui.splash.Splash

@Composable
fun NavController(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screens.SplashScreen.route
    ){
        composable(route = Screens.SplashScreen.route){
            Splash(navHostController = navHostController)
        }
        composable(route = Screens.ContactsScreen.route){
            Contacts(navHostController = navHostController)
        }
        composable(
            route = Screens.ContactDetailScreen.route.plus("/{contactId}"),
            arguments = listOf(navArgument("contactId"){
                type = NavType.IntType
            })
        ){
            ContactDetail(navHostController = navHostController)
        }
        composable(route = Screens.MessagesScreen.route){
            Messages(navHostController = navHostController)
        }
    }
}