package com.mukesh.contactmachinetask.presentation.ui.contactDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukesh.contactmachinetask.common.NetworkState
import com.mukesh.contactmachinetask.common.UiState
import com.mukesh.contactmachinetask.common.generateRandomOtp
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto
import com.mukesh.contactmachinetask.domain.usecase.contacts.GetContactDetailUseCase
import com.mukesh.contactmachinetask.domain.usecase.messages.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ContactDetailVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getContactDetailUseCase: GetContactDetailUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel(){

    private val contactId by lazy { savedStateHandle.get<Int>("contactId") }

    init {
        contactId?.let {
            getContactDetail(it)
        } ?: run {
            _contactDetailState.value = UiState(error = Exception("Contact Not Found"))
        }
    }


    /**
     * Contact Detail
     * */
    private val _contactDetailState = mutableStateOf<UiState<ContactDto?>>(UiState())
    val contactDetailState : State<UiState<ContactDto?>> = _contactDetailState

    private fun getContactDetail(id: Int) = getContactDetailUseCase(id = id).onEach { result ->
        when(result){
            is NetworkState.Error -> {
                _contactDetailState.value = UiState(error = result.exception)
            }
            is NetworkState.Success -> {
                _contactDetailState.value = UiState(data = result.data)
            }
            else -> Unit
        }
    }.launchIn(viewModelScope)


    /**
     * Send Message
     * */
    private val _sendMessageState = mutableStateOf<UiState<String>>(UiState())
    val sendMessageState : State<UiState<String>> = _sendMessageState

    fun sendMessage(contactDto: ContactDto) = sendMessageUseCase(
        contactDto = contactDto,
        message = "Hi, Your OTP is: ${generateRandomOtp()}"
    ).onEach { result ->
        when(result){
            is NetworkState.Loading -> {
                _sendMessageState.value = UiState(isLoading = true)
            }
            is NetworkState.Error -> {
                _sendMessageState.value = UiState(error = result.exception)
            }
            is NetworkState.Success -> {
                _sendMessageState.value = UiState(data = result.data)
            }
        }
    }.launchIn(viewModelScope)

}