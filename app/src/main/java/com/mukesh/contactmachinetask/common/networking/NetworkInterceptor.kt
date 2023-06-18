package com.mukesh.contactmachinetask.common.networking

import android.util.Base64
import com.mukesh.contactmachinetask.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object NetworkInterceptor {


    /**
     * Status Code Handler
     * */
    val interceptor = Interceptor { chain ->
        var request = chain.request()

        /** Handle Data In Cache */

        /** Handle Data In Cache */
        request = request.newBuilder().apply {
            header("Authorization", "Basic ${Base64.encodeToString("${BuildConfig.API_KEY}:${BuildConfig.API_SECRET}".toByteArray(), android.util.Base64.NO_WRAP)}")
            method(request.method, request.body)
        }.build()


        /**
         * Handle Api Response
         * */
        /**
         * Handle Api Response
         * */
        val response = chain.proceed(request)
        handleStatueCode(response)

        response
    }


    /**
     * Handle Status Code
     * */
    private fun handleStatueCode(response: Response) {
        //Check Status Code
        when (response.code) {
            //Handle Codes
            401 -> Unit
        }
    }

}