package com.mukesh.contactmachinetask.data.repository

import com.mukesh.contactmachinetask.common.networking.ApiHandler
import com.mukesh.contactmachinetask.domain.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiHandler: ApiHandler
): Repository {


    /**
     * Send Otp
     * */
    override suspend fun sendOtp(hashMap: HashMap<String, String>): Response<Any> {
        return apiHandler.sendOtp(hashMap = hashMap)
    }
}