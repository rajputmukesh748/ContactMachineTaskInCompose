package com.mukesh.contactmachinetask.domain.repository

import retrofit2.Response

interface Repository {

    suspend fun sendOtp(hashMap: HashMap<String, String>): Response<Any>

}