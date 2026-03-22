package com.mad.cw21997.ui

import com.mad.cw21997.data.Tent

sealed interface TentListUIState {

    data class Success(val tentList: List<Tent>) : TentListUIState
    object Error: TentListUIState
    object Loading : TentListUIState

}
