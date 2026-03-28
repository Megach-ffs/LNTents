package com.mad.cw21997.ui

sealed class UiMessage {
    data class Plain(val resId: Int) : UiMessage()
    data class WithArgs(val resId: Int, val args: List<String>) : UiMessage()
}
