package com.example.dogsjetpack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


// Dog api model
@Entity
data class DogModel(

    @ColumnInfo(name = "bred_for")
    @SerializedName("bred_for")
    var bred_for: String?,

    @ColumnInfo(name =  "breed_group")
    @SerializedName("breed_group")
    var group: String?,

    @ColumnInfo(name = "id")
    @SerializedName("id")
    var dogId: String?,

    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    var lifeSpan: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var dogName: String?,

    @ColumnInfo(name = "temperament")
    @SerializedName("temperament")
    var temperament: String?,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}