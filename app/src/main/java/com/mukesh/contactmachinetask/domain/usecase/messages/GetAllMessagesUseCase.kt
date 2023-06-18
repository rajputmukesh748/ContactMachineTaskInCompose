package com.mukesh.contactmachinetask.domain.usecase.messages

import com.mukesh.contactmachinetask.common.NetworkState
import com.mukesh.contactmachinetask.data.remote.dto.MessagesDto
import com.mukesh.contactmachinetask.db.ContactDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllMessagesUseCase @Inject constructor(
    private val contactDb: ContactDb
) {

    operator fun invoke(): Flow<NetworkState<List<MessagesDto>>> = flow {
        try {
            emit(NetworkState.Loading())
            val data = getAllMessages()
            if (data.isNullOrEmpty()) throw Exception()
            emit(NetworkState.Success(data = data))
        }catch (e:Exception){
            emit(NetworkState.Error(e))
        }
    }.flowOn(Dispatchers.IO)


    /**
     * Get All Messages
     * */
    private fun getAllMessages(): List<MessagesDto>? = contactDb.messagesDao().getAllMessages()

}