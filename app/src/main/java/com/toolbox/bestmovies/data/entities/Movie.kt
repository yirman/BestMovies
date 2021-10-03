package com.toolbox.bestmovies.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies",
    foreignKeys = [ForeignKey(
        entity = Carousel::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("carouselId"),
        onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("carouselId")]
)
data class Movie (
    var carouselId: Long? = null,
    @PrimaryKey(autoGenerate = true) var id: Int,
    @SerializedName("title") var title : String? = null,
    @SerializedName("imageUrl") var imageUrl : String? = null,
    @SerializedName("videoUrl") var videoUrl : String? = null,
    @SerializedName("description") var description : String? = null,
)