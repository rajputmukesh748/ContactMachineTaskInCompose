package com.mukesh.contactmachinetask.domain.usecase.messages

import com.mukesh.contactmachinetask.common.NetworkState
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto
import com.mukesh.contactmachinetask.data.remote.dto.MessagesDto
import com.mukesh.contactmachinetask.db.ContactDb
import com.mukesh.contactmachinetask.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val contactDb: ContactDb,
    private val repository: Repository
) {

    operator fun invoke(contactDto: ContactDto, message: String) : Flow<NetworkState<String>> = flow {
        try {
            emit(NetworkState.Loading())
            val response = sendMessage(contactDto = contactDto, message = message)
            if (response.isSuccessful){
                saveMessage(contactDto, message)
                emit(NetworkState.Success(System.currentTimeMillis().toString()))
            }else throw Exception(response.errorBody()?.string().orEmpty())
        }catch (e:Exception){
            emit(NetworkState.Error(e))
        }
    }.flowOn(Dispatchers.IO)


    /**
     * Save Message
     * */
    private suspend fun saveMessage(contactDto: ContactDto, message: String){
        contactDb.messagesDao().addMessage(MessagesDto(
            id = null,
            countryCode = contactDto.countryCode,
            phoneNumber = contactDto.phoneNumber,
            firstName = contactDto.firstName,
            lastName = contactDto.lastName,
            message = message
        ))
    }


    /**
     * Send SMS
     * */
    private suspend fun sendMessage(contactDto: ContactDto, message: String): Response<Any>{
        return repository.sendOtp(hashMap = hashMapOf(
            "from" to "Vonage APIs",
            "to" to "+${contactDto.countryCode}${contactDto.phoneNumber}",
            "text" to message
        ))
    }

}