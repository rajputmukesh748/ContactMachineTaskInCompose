package com.mukesh.contactmachinetask.presentation.ui.contacts

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukesh.contactmachinetask.common.NetworkState
import com.mukesh.contactmachinetask.common.UiState
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto
import com.mukesh.contactmachinetask.domain.usecase.contacts.GetAllContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ContactsVM @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase
) : ViewModel() {

    init {
        getAllContacts()
    }

    private val _allContactsState = mutableStateOf<UiState<List<ContactDto>?>>(UiState())
    val allContactsState: State<UiState<List<ContactDto>?>> = _allContactsState

    private fun getAllContacts() = getAllContactsUseCase().onEach { result ->
        when(result){
            is NetworkState.Loading -> {
                _allContactsState.value = UiState(isLoading = true)
            }
            is NetworkState.Error ->{
                _allContactsState.value = UiState(error = result.exception)
            }
            is NetworkState.Success -> {
                _allContactsState.value = UiState(data = result.data)
            }
        }
    }.launchIn(viewModelScope)

}