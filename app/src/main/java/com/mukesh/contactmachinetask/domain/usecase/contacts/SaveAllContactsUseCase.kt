package com.mukesh.contactmachinetask.domain.usecase.contacts

import android.util.Log
import com.mukesh.contactmachinetask.common.NetworkState
import com.mukesh.contactmachinetask.common.UiState
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto
import com.mukesh.contactmachinetask.db.ContactDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class SaveAllContactsUseCase @Inject constructor(
    private val contactDb: ContactDb
) {

    operator fun invoke(list: List<ContactDto>?): Flow<NetworkState<String?>> = flow {
        try {
            emit(NetworkState.Loading())
            saveAllContacts(list = list)
            emit(NetworkState.Success(System.currentTimeMillis().toString() ?: null))
        }catch (e:Exception){
            emit(NetworkState.Error(e))
        }
    }.flowOn(Dispatchers.IO)


    /**
     * Save All Contacts
     * */
    private suspend fun saveAllContacts(list: List<ContactDto>?) {
        contactDb.contactsDao().deleteAllContacts()
        contactDb.contactsDao().addContacts(list)
    }

}