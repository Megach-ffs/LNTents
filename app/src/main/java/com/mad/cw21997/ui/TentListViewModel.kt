package com.mad.cw21997.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mad.cw21997.R
import com.mad.cw21997.TentApplication
import com.mad.cw21997.data.NetworkTentRepository
import com.mad.cw21997.data.Tent
import com.mad.cw21997.data.TentRepository
import com.mad.cw21997.data.TentTestData
//import com.mad.cw21997.network.TentApi
import com.mad.cw21997.network.TentApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class TentListViewModel(private val tentRepository: TentRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<TentListUIState>(TentListUIState.Loading)
    val uiState: StateFlow<TentListUIState> = _uiState.asStateFlow()

    private val _userMessage = MutableSharedFlow<String>()
    val userMessage = _userMessage.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(500) 
            fetchData()
        }
    }

//    fun fetchData(){
//
////        val tentList = TentTestData().tentList
////        _uiState.value = TentListUIState()
//    }

    fun fetchData() {
        viewModelScope.launch {

            try {

                val listResult = tentRepository.getTentData()
                _uiState.value = TentListUIState.Success(listResult)
            } catch (e: Exception) {
                _uiState.value = TentListUIState.Error
            }
        }
    }
    
    fun increaseStock(tent: Tent){
        viewModelScope.launch {
            try {
                val updatedTent = tent.copy(stock = tent.stock + 1)
                tentRepository.updateTent(updatedTent)
                fetchData()
                _userMessage.emit("Stock increased for ${tent.name}")
            } catch (e: Exception) {
                _userMessage.emit("Failed to update stock")
            }
        }
    }

    fun decreaseStock(tent: Tent){
        if (tent.stock <= 0) return
        viewModelScope.launch {
            try {
                val updatedTent = tent.copy(stock = tent.stock - 1)
                tentRepository.updateTent(updatedTent)
                fetchData()
                _userMessage.emit("Stock decreased for ${tent.name}")
            } catch (e: Exception) {
                _userMessage.emit("Failed to update stock")
            }
        }
    }

    fun deleteTent(tent: Tent){
        viewModelScope.launch {
            try {
                tentRepository.deleteTent(tent)
                fetchData()
                _userMessage.emit("Tent deleted: ${tent.name}")
            } catch (e: Exception) {
                _userMessage.emit("Error deleting tent")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TentApplication)
                val tentRepository = application.container.tentRepository
                TentListViewModel(tentRepository = tentRepository)
            }
        }
    }


}
