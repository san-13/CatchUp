package com.sv.catchup.ui.home

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.sv.catchup.data.TextFieldState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class homeViewModel:ViewModel() {
    private val _roomName = mutableStateOf(TextFieldState())
    val roomName: State<TextFieldState> = _roomName

    private val _onJoinEvent = MutableSharedFlow<String>()
    val onJoinEvent = _onJoinEvent.asSharedFlow()

    fun onRoomEnter(name: String) {
        _roomName.value = roomName.value.copy(
            text = name
        )
    }

    fun onJoinRoom() {
        if(roomName.value.text.isBlank()) {
            _roomName.value = roomName.value.copy(
                error = "The room can't be empty"
            )
            return
        }
        viewModelScope.launch {
            _onJoinEvent.emit(roomName.value.text)
        }
    }
}