package com.mukesh.contactmachinetask.presentation.ui.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukesh.contactmachinetask.common.NetworkState
import com.mukesh.contactmachinetask.common.UiState
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto
import com.mukesh.contactmachinetask.domain.usecase.contacts.SaveAllContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    private val saveAllContactsUseCase: SaveAllContactsUseCase
): ViewModel() {

    private val _saveContactState = mutableStateOf<UiState<String?>>(UiState())
    val saveContactState: State<UiState<String?>> = _saveContactState

    fun saveAllContacts(list: List<ContactDto>?) = saveAllContactsUseCase(list = list).onEach { result ->
       when(result){
           is NetworkState.Loading -> {
               _saveContactState.value = UiState(isLoading = true)
           }
           is NetworkState.Error -> {
               _saveContactState.value = UiState(error = result.exception)
           }
           is NetworkState.Success -> {
               _saveContactState.value = UiState(data = result.data)
           }
       }
    }.launchIn(viewModelScope)

}