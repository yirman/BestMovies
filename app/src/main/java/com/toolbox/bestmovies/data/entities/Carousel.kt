package com.toolbox.bestmovies.data.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "carousels")
data class Carousel @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @SerializedName("title") var title : String? = null,
    @SerializedName("type") var type : String? = null,
    @SerializedName("items") @Ignore var items : List<Movie>? = null
)