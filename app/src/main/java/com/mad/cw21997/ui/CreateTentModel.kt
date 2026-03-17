package com.mad.cw21997.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateTentModel: ViewModel() {

    private val _uiState = MutableStateFlow(CreateTentUIState())
    val uiState: StateFlow<CreateTentUIState> = _uiState.asStateFlow()



}