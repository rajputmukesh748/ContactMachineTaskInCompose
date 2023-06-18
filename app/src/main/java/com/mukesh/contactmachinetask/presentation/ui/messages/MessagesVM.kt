package com.mukesh.contactmachinetask.presentation.ui.messages

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukesh.contactmachinetask.common.NetworkState
import com.mukesh.contactmachinetask.common.UiState
import com.mukesh.contactmachinetask.data.remote.dto.MessagesDto
import com.mukesh.contactmachinetask.domain.usecase.messages.GetAllMessagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MessagesVM @Inject constructor(
    private val getAllMessagesUseCase: GetAllMessagesUseCase
) : ViewModel() {


    /**
     * Get All Messages
     * */
    private val _allMessagesState = mutableStateOf<UiState<List<MessagesDto>?>>(UiState())
    val allMessagesState : State<UiState<List<MessagesDto>?>> = _allMessagesState

    fun getAllMessages() = getAllMessagesUseCase().onEach { result ->
        when(result){
            is NetworkState.Error -> {
                _allMessagesState.value = UiState(error = result.exception)
            }
            is NetworkState.Success -> {
                _allMessagesState.value = UiState(data = result.data)
            }
            else -> Unit
        }
    }.launchIn(viewModelScope)

}