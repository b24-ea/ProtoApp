package com.aldanmaz.skyq.model

import com.google.gson.annotations.SerializedName

data class Images(

    @SerializedName("url")
    val url: String?,

    @SerializedName("type")
    val type: String?

)
