package com.mukesh.contactmachinetask.data.remote.dto


import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
@Entity
data class MessagesDto(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @SerializedName("countryCode")
    val countryCode: Int? = null,
    @SerializedName("createdAt")
    val createdAt: Long? = System.currentTimeMillis(),
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber: String? = null,
    @SerializedName("message")
    val message: String? = null
)