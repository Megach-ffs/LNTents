package com.mad.cw21997.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TentResponse(
    val code: Int,
    val status: String,
    val message: String? = null,
    val data: List<TentData> = emptyList()
)
@Serializable
data class TentData (
    val id: Int,
    val title: String = "",
    val description: String = "",
    val size: String = "",
    @SerialName("integer_one")
    val weight: String = "",
    @SerialName("integer_two")
    val waterProof: String = "",
    val type: String = "",
    @SerialName("integer_three")
    val stock: String = "",
    val url: String = ""
)
