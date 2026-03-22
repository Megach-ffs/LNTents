package com.mad.cw21997.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mad.cw21997.TentApplication
import com.mad.cw21997.data.Tent
import com.mad.cw21997.data.TentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateTentModel(private val tentRepository: TentRepository): ViewModel() {

    private val _uiState = MutableStateFlow(CreateTentUIState())
    val uiState: StateFlow<CreateTentUIState> = _uiState.asStateFlow()

    fun initiateEdit(tent: Tent) {
        _uiState.update { 
            it.copy(
                id = tent.id,
                name = tent.name,
                brand = tent.brand,
                capacity = tent.capacity,
                weight = tent.weight,
                waterProof = tent.waterProof,
                type = tent.type,
                stock = tent.stock,
                imageUrl = tent.imageUrl,
                editMode = true
            )
        }
    }

    fun clearForm() {
        _uiState.value = CreateTentUIState()
    }

    fun updateName(name: String) { _uiState.update { it.copy(name = name) } }
    fun updateBrand(brand: String) { _uiState.update { it.copy(brand = brand) } }
    fun updateCapacity(capacity: String) { _uiState.update { it.copy(capacity = capacity.toIntOrNull() ?: 0) } }
    fun updateWeight(weight: String) { _uiState.update { it.copy(weight = weight.toIntOrNull() ?: 0) } }
    fun updateWaterProof(waterProof: String) { _uiState.update { it.copy(waterProof = waterProof.toIntOrNull() ?: 0) } }
    fun updateType(type: String) { _uiState.update { it.copy(type = type) } }
    fun updateStock(stock: String) { _uiState.update { it.copy(stock = stock.toIntOrNull() ?: 0) } }
    fun updateImageUrl(imageUrl: String) { _uiState.update { it.copy(imageUrl = imageUrl) } }

    fun saveTent(onSuccess: () -> Unit = {}) {

        val tent = Tent(
            id = _uiState.value.id,
            name = _uiState.value.name,
            brand = _uiState.value.brand,
            capacity = _uiState.value.capacity,
            weight = _uiState.value.weight,
            waterProof = _uiState.value.waterProof,
            type = _uiState.value.type,
            stock = _uiState.value.stock,
            imageUrl = _uiState.value.imageUrl
        )

        viewModelScope.launch {
            try {
                if (_uiState.value.editMode) {
                    tentRepository.updateTent(tent)
                } else {
                    tentRepository.postTent(tent)
                }
                clearForm()

                onSuccess()
            } catch (e: Exception){

            }
        }


    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TentApplication)
                val tentRepository = application.container.tentRepository
                CreateTentModel(tentRepository = tentRepository)
            }
        }
    }
}
