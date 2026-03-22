package com.mad.cw21997.ui

data class CreateTentUIState (
    val id: Int = 0,
    val name: String = "",
    val brand: String = "",
    val capacity: Int = 0,
    val weight: Int = 0,
    val waterProof: Int = 0,
    val type: String = "",
    val stock: Int = 0,
    val imageUrl: String = "",
    val editMode: Boolean = false

)