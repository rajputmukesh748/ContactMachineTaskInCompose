package com.mukesh.contactmachinetask.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ContactJsonDto(
    @SerializedName("contacts")
    val contacts: List<ContactDto>? = null
)