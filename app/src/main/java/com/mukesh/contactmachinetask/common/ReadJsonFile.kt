package com.mukesh.contactmachinetask.common

import android.content.Context
import androidx.compose.runtime.Composable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto
import com.mukesh.contactmachinetask.data.remote.dto.ContactJsonDto


/**
 * Read Json File Data
 * */
fun Context.readJsonFile(fileName: String) = assets.open(fileName).bufferedReader().use {  data ->
    val response = data.readText()
    response.convertStringIntoClass<ContactJsonDto>().contacts ?: emptyList()
}


/**
 * Convert any data into json data
 * */
fun Any.convertGsonString(): String = Gson().toJson(this)


/**
 * Convert String into Data Class
 * */
inline fun <reified T> String.convertStringIntoClass(): T = Gson().fromJson(this, T::class.java)