package com.mad.cw21997.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mad.cw21997.R
import com.mad.cw21997.TentApplication
import com.mad.cw21997.data.Tent
import com.mad.cw21997.data.TentRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class TentListViewModel(private val tentRepository: TentRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<TentListUIState>(TentListUIState.Loading)
    val uiState: StateFlow<TentListUIState> = _uiState.asStateFlow()

    private val _userMessage = MutableSharedFlow<UiMessage>()
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
            } catch (e: IOException) {
                _uiState.value = TentListUIState.Error
                _userMessage.emit(UiMessage.Plain(R.string.message_network_error))
                Log.e("TentListViewModel", "Network error", e)
            } catch (e: Exception) {
                _uiState.value = TentListUIState.Error
                _userMessage.emit(UiMessage.Plain(R.string.message_unknown_error))
                Log.e("TentListViewModel", "Unknown error", e)
            }
        }
    }
    
    fun increaseStock(tent: Tent){
        viewModelScope.launch {
            try {
                val updatedTent = tent.copy(stock = tent.stock + 1)
                tentRepository.updateTent(updatedTent)
                fetchData()
                _userMessage.emit(UiMessage.WithArgs(R.string.message_stock_increased, listOf(tent.name)))
            } catch (e: Exception) {
                _userMessage.emit(UiMessage.Plain(R.string.message_update_failed))
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
                _userMessage.emit(UiMessage.WithArgs(R.string.message_stock_decreased, listOf(tent.name)))
            } catch (e: Exception) {
                _userMessage.emit(UiMessage.Plain(R.string.message_update_failed))
            }
        }
    }

    fun deleteTent(tent: Tent){
        viewModelScope.launch {
            try {
                tentRepository.deleteTent(tent)
                fetchData()
                _userMessage.emit(UiMessage.WithArgs(R.string.message_tent_deleted, listOf(tent.name)))
            } catch (e: Exception) {
                _userMessage.emit(UiMessage.Plain(R.string.message_delete_failed))
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
