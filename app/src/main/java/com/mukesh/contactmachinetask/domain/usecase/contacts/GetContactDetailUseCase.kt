package com.mukesh.contactmachinetask.domain.usecase.contacts

import com.mukesh.contactmachinetask.common.NetworkState
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto
import com.mukesh.contactmachinetask.db.ContactDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetContactDetailUseCase @Inject constructor(
    private val contactDb: ContactDb
) {

    operator fun invoke(id: Int): Flow<NetworkState<ContactDto?>> = flow {
        try {
            emit(NetworkState.Loading())
            val data = getContact(id = id)
            emit(NetworkState.Success(data))
        }catch (e:Exception){
            emit(NetworkState.Error(e))
        }
    }.flowOn(Dispatchers.IO)


    suspend fun getContact(id: Int) = contactDb.contactsDao().getContactDetail(id = id)

}