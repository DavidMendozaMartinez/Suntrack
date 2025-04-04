package com.davidmendozamartinez.domain.place.model

import java.util.UUID

data class Place(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
) {
    companion object {
        fun generateUUID(
            latitude: Double,
            longitude: Double,
        ): String = UUID.nameUUIDFromBytes("$latitude,$longitude".toByteArray()).toString()
    }
}
