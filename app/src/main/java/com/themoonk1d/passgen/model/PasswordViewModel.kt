package com.themoonk1d.passgen.model

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.themoonk1d.passgen.State.PasswordUiState
import com.themoonk1d.passgen.data.charList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PasswordViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(PasswordUiState())
    val uiState: StateFlow<PasswordUiState> = _uiState.asStateFlow()
    private var generatedPassword by mutableStateOf("")

    init {
        setPassword()
    }



    fun setPassword(){
        generatePassword()
        _uiState.value = PasswordUiState(passwordText = generatedPassword.take(_uiState.value.passwordLength))
    }

    private fun generatePassword(){
        val password = StringBuilder()
        while(password.length <= charList.size){
            val rand = (1..85).random()
            password.append(charList[rand])
        }
        generatedPassword = password.toString()
    }

    fun setPasswordLength(length : Int){
        println("Password: $generatedPassword")
        println("Password set to: ${generatedPassword.take(length)}")
        _uiState.update {
            it.copy(
                passwordText = generatedPassword.take(length),
                passwordLength = length
            )
        }
    }

    fun copyToClipboard(context: Context, label: String, value: String) {
        println("Copy")
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, value)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
    }


}