package com.mad.cw21997.data

//import com.mad.cw21997.network.TentApi
import com.mad.cw21997.network.TentApiService
import com.mad.cw21997.network.TentData

interface TentRepository {
    suspend fun getTentData(): List<Tent>
    suspend fun postTent(tent: Tent)
    suspend fun updateTent(tent: Tent)
    suspend fun deleteTent(tent: Tent)
}

class NetworkTentRepository(private val tentApiService: TentApiService) : TentRepository {
    override suspend fun getTentData(): List<Tent> {
        return tentApiService.getPhotos().data.map { tentData ->
            Tent(
                id = tentData.id,
                name = tentData.title,
                brand = tentData.description,
                capacity = tentData.size.toIntOrNull() ?: 0,
                waterProof = tentData.waterProof.toIntOrNull() ?: 0,
                weight = tentData.weight.toIntOrNull() ?: 0,
                type = tentData.type,
                stock = tentData.stock.toIntOrNull() ?: 0,
                imageUrl = tentData.url
            )
        }
    }

    override suspend fun postTent(tent: Tent) {
        tentApiService.postTent(tentData = tent.toNetworkModel())
    }

    override suspend fun updateTent(tent: Tent){
        tentApiService.updateTent(tentData = tent.toNetworkModel(), id = tent.id)
    }

    override suspend fun deleteTent(tent: Tent){
        tentApiService.deleteTent(id = tent.id)
    }

    private fun Tent.toNetworkModel(): TentData = TentData(
        id = this.id,
        title = this.name,
        description = this.brand,
        size = this.capacity.toString(),
        weight = this.weight.toString(),
        waterProof = this.waterProof.toString(),
        type = this.type,
        stock = this.stock.toString(),
        url = this.imageUrl
    )
}
