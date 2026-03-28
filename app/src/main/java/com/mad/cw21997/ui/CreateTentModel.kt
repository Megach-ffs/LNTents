package com.mad.cw21997.ui

import android.util.Patterns
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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateTentModel(private val tentRepository: TentRepository): ViewModel() {

    private val _uiState = MutableStateFlow(CreateTentUIState())
    val uiState: StateFlow<CreateTentUIState> = _uiState.asStateFlow()
    
    private val _userMessage = MutableSharedFlow<UiMessage>()
    val userMessage = _userMessage.asSharedFlow()


    fun initiateEdit(tent: Tent) {
        _uiState.update { 
            it.copy(
                id = tent.id,
                name = tent.name,
                brand = tent.brand,
                capacity = tent.capacity.toString(),
                weight = tent.weight.toString(),
                waterProof = tent.waterProof.toString(),
                type = tent.type,
                stock = tent.stock.toString(),
                imageUrl = tent.imageUrl,
                editMode = true
            )
        }
        // Run validations on initial data
        validateName(_uiState.value.name)
        validateBrand(_uiState.value.brand)
        validateCapacity(_uiState.value.capacity)
        validateWeight(_uiState.value.weight)
        validateWaterProof(_uiState.value.waterProof)
        validateStock(_uiState.value.stock)
        validateImageUrl(_uiState.value.imageUrl)
    }

    fun clearForm() {
        _uiState.value = CreateTentUIState()
    }

    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
        validateName(name)
    }

    private fun validateName(name: String) {
        val error = when {
            name.isBlank() -> R.string.error_name_empty
            name.length < 3 -> R.string.error_name_short
            name.length > 50 -> R.string.error_name_long
            else -> null
        }
        _uiState.update { it.copy(nameError = error) }
    }

    fun updateBrand(brand: String) {
        _uiState.update { it.copy(brand = brand) }
        validateBrand(brand)
    }

    private fun validateBrand(brand: String) {
        val error = when {
            brand.isBlank() -> R.string.error_brand_empty
            brand.length < 2 -> R.string.error_brand_short
            else -> null
        }
        _uiState.update { it.copy(brandError = error) }
    }

    fun updateCapacity(capacity: String) {
        _uiState.update { it.copy(capacity = capacity) }
        validateCapacity(capacity)
    }

    private fun validateCapacity(capacity: String) {
        val intVal = capacity.toIntOrNull()
        val error = when {
            capacity.isBlank() -> R.string.error_capacity_empty
            intVal == null -> R.string.error_invalid_integer
            intVal <= 0 -> R.string.error_capacity_min
            else -> null
        }
        _uiState.update { it.copy(capacityError = error) }
    }

    fun updateWeight(weight: String) {
        _uiState.update { it.copy(weight = weight) }
        validateWeight(weight)
    }

    private fun validateWeight(weight: String) {
        val intVal = weight.toIntOrNull()
        val error = when {
            weight.isBlank() -> R.string.error_weight_empty
            intVal == null -> R.string.error_invalid_integer
            intVal <= 0 -> R.string.error_weight_min
            else -> null
        }
        _uiState.update { it.copy(weightError = error) }
    }

    fun updateWaterProof(waterProof: String) {
        _uiState.update { it.copy(waterProof = waterProof) }
        validateWaterProof(waterProof)
    }

    private fun validateWaterProof(waterProof: String) {
        val intVal = waterProof.toIntOrNull()
        val error = when {
            waterProof.isBlank() -> R.string.error_waterproof_empty
            intVal == null -> R.string.error_invalid_integer
            else -> null
        }
        _uiState.update { it.copy(waterProofError = error) }
    }

    fun updateType(type: String) {
        _uiState.update { it.copy(type = type) }
    }

    fun updateStock(stock: String) {
        _uiState.update { it.copy(stock = stock) }
        validateStock(stock)
    }

    private fun validateStock(stock: String) {
        val intVal = stock.toIntOrNull()
        val error = when {
            stock.isBlank() -> R.string.error_stock_empty
            intVal == null -> R.string.error_invalid_integer
            intVal < 0 -> R.string.error_stock_negative
            else -> null
        }
        _uiState.update { it.copy(stockError = error) }
    }

    fun updateImageUrl(imageUrl: String) {
        _uiState.update { it.copy(imageUrl = imageUrl) }
        validateImageUrl(imageUrl)
    }

    private fun validateImageUrl(url: String) {
        val error = when {
            url.isNotBlank() && !Patterns.WEB_URL.matcher(url).matches() -> R.string.error_invalid_url
            else -> null
        }
        _uiState.update { it.copy(imageUrlError = error) }
    }

    fun saveTent(onSuccess: () -> Unit = {}) {
        if (!_uiState.value.isFormValid) return

        val state = _uiState.value
        val tent = Tent(
            id = state.id,
            name = state.name,
            brand = state.brand,
            capacity = state.capacity.toIntOrNull() ?: 0,
            weight = state.weight.toIntOrNull() ?: 0,
            waterProof = state.waterProof.toIntOrNull() ?: 0,
            type = state.type,
            stock = state.stock.toIntOrNull() ?: 0,
            imageUrl = state.imageUrl
        )

        viewModelScope.launch {
            try {
                if (state.editMode) {
                    tentRepository.updateTent(tent)
                } else {
                    tentRepository.postTent(tent)
                }
                clearForm()
                onSuccess()
                _userMessage.emit(UiMessage.Plain(R.string.message_tent_saved))
            } catch (e: Exception) {
                _userMessage.emit(UiMessage.Plain(R.string.message_error_saving))
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
