package com.aldanmaz.skyq.model


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName




data class Proto(

    @SerializedName("id")
    val id : Int,


    @SerializedName("title")
    val title : String?,


    @SerializedName("description")
    val description : String?,


    @SerializedName("duration")
    val duration : String?,


    @SerializedName("releaseDate")
    val releaseDate : String?,


    @SerializedName("images")
    val images : List<Images>){

        @PrimaryKey(autoGenerate = true)
         var uuid : Int = 0
}


