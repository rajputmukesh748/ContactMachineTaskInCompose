package com.mukesh.contactmachinetask.presentation.navController

sealed class Screens(val route: String) {

    object SplashScreen: Screens("splash_screen")

    object ContactsScreen: Screens("contacts_screen")

    object ContactDetailScreen: Screens("contact_detail_screen")

    object MessagesScreen: Screens("messages_screen")

    object SendMessageScreen: Screens("send_message_screen")

}