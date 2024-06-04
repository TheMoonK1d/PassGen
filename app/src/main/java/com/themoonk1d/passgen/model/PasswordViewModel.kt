package com.themoonk1d.passgen.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.themoonk1d.passgen.State.PasswordUiState
import com.themoonk1d.passgen.data.charList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(PasswordUiState())
    val uiState: StateFlow<PasswordUiState> = _uiState.asStateFlow()
    private var generatedPassword by mutableStateOf("")

    init {
        setPassword()
    }



    fun setPassword(){
        generatePassword()
        _uiState.value = PasswordUiState(passwordText = generatedPassword)
    }

    private fun generatePassword(){
        val password = StringBuilder()
        while(password.length <= charList.size){
            val rand = (1..85).random()
            password.append(charList[rand])
        }
        generatedPassword = password.toString().take(28)
    }
}