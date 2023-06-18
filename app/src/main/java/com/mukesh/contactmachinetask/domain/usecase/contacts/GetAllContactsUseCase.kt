package com.mukesh.contactmachinetask.domain.usecase.contacts

import com.mukesh.contactmachinetask.common.NetworkState
import com.mukesh.contactmachinetask.common.UiState
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto
import com.mukesh.contactmachinetask.db.ContactDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllContactsUseCase @Inject constructor(
    private val contactDb: ContactDb
) {

    operator fun invoke() : Flow<NetworkState<List<ContactDto>?>> = flow {
        try {
            emit(NetworkState.Loading())
            val data = getAllContact()
            emit(NetworkState.Success(data = data))
        }catch (e:Exception){
            emit(NetworkState.Error(e))
        }
    }.flowOn(Dispatchers.IO)


    /**
     * Get All Contacts
     * */
    private fun getAllContact(): List<ContactDto>? = contactDb.contactsDao().getAllContacts()
}