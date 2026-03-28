package com.mad.cw21997.ui

data class CreateTentUIState (
    val id: Int = 0,
    val name: String = "",
    val nameError: Int? = null,
    val brand: String = "",
    val brandError: Int? = null,
    val capacity: String = "",
    val capacityError: Int? = null,
    val weight: String = "",
    val weightError: Int? = null,
    val waterProof: String = "",
    val waterProofError: Int? = null,
    val type: String = "Dome",
    val stock: String = "",
    val stockError: Int? = null,
    val imageUrl: String = "",
    val imageUrlError: Int? = null,
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
