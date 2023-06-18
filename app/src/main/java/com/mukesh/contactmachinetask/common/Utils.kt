package com.mukesh.contactmachinetask.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Handle Exceptions
 * We don't need to add try/catch everytime
 * */
fun safeCall(callback: () -> Unit) = try {
    callback()
} catch (e:Exception){
    e.printStackTrace()
}

@Composable
fun ShowSnackBar(string: String){
    val showDialog = remember {
        mutableStateOf(true)
    }
    if (showDialog.value){
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },
            confirmButton = {
                Button(onClick = { showDialog.value = false })
                {
                    Text(text = "OK")
                }
            },
            title = {
                Text(text = LocalContext.current.getString(STRING_ALIAS.app_name))
            },
            text = {
                Text(text = string.getServerError())
            }
        )
    }

}


/**
 * Generate Random Otp
 * */
fun generateRandomOtp(): Int = ((Math.random() * 9000) + 1000).toInt()


/**
 * Convert Date
 * */
fun Long?.formatDate(outputFormat: String = "dd MMM, yyyy hh:mm a"): String {
    return try {
        if (this == null) throw Exception()
        val dateFormatter = SimpleDateFormat(outputFormat, Locale.getDefault())
        dateFormatter.format(Date(this))
    }catch (e:Exception){
        "N/A"
    }
}


/**
 * Get Server Error Message
 * */
fun String.getServerError(): String = try {
    JSONObject(this).optString("message")
}catch (e:Exception){
    this
}