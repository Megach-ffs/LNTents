package com.mad.cw21997.data

import androidx.lifecycle.ViewModel
import com.mad.cw21997.R

class TentTestData : ViewModel() {
    val tentList = listOf(
        Tent(
            id = 1,
            name = "Eco-Friendly Dome",
            brand = "GreenPeak",
            capacity = 2,
            waterProof = 3000,
            weight = 2400,
            type = "Dome",
            stock = 12,
            imageUrl = "",
            imageResourceId = R.drawable.tent1
        ),
        Tent(
            id = 2,
            name = "Alpine Explorer",
            brand = "SummitGear",
            capacity = 4,
            waterProof = 5000,
            weight = 4200,
            type = "Geodesic",
            stock = 5,
            imageUrl = "",
            imageResourceId = R.drawable.tent1
        ),
        Tent(
            id = 3,
            name = "Family Retreat",
            brand = "TrailHome",
            capacity = 6,
            waterProof = 2000,
            weight = 8500,
            type = "Tunnel",
            stock = 8,
            imageUrl = "",
            imageResourceId = R.drawable.tent1
        ),
        Tent(
            id = 4,
            name = "Solo Hiker Ultra",
            brand = "SwiftPath",
            capacity = 1,
            waterProof = 1500,
            weight = 950,
            type = "Backpacking",
            stock = 15,
            imageUrl = "",
            imageResourceId = R.drawable.tent1
        )
    )
}