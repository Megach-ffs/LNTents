package com.mad.cw21997.ui

data class CreateTentUIState (
    val id: Int = 0,
    val name: String = "",
    val nameError: String? = null,
    val brand: String = "",
    val brandError: String? = null,
    val capacity: String = "",
    val capacityError: String? = null,
    val weight: String = "",
    val weightError: String? = null,
    val waterProof: String = "",
    val waterProofError: String? = null,
    val type: String = "Dome",
    val stock: String = "",
    val stockError: String? = null,
    val imageUrl: String = "",
    val imageUrlError: String? = null,
    val editMode: Boolean = false
) {
    val isFormValid: Boolean
        get() = name.isNotBlank() && nameError == null &&
                brand.isNotBlank() && brandError == null &&
                capacity.isNotBlank() && capacityError == null &&
                weight.isNotBlank() && weightError == null &&
                waterProof.isNotBlank() && waterProofError == null &&
                stock.isNotBlank() && stockError == null &&
                imageUrlError == null
}
