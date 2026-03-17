package com.mad.cw21997.ui

import androidx.lifecycle.ViewModel
import com.mad.cw21997.R
import com.mad.cw21997.data.Tent
import com.mad.cw21997.data.TentTestData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TentListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TentListUIState())
    val uiState: StateFlow<TentListUIState> = _uiState.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData(){

        val tentList = TentTestData().tentList
        _uiState.value = TentListUIState(tentList = tentList)
    }


}
