package com.davidmendozamartinez.database.table.place.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davidmendozamartinez.domain.place.model.Place

@Entity(tableName = "place")
data class PlaceEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
)

fun Place.toPlaceEntity(): PlaceEntity = PlaceEntity(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude,
)

fun PlaceEntity.toPlace(): Place = Place(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude,
)
