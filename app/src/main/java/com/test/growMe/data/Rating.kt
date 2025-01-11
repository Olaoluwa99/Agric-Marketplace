package com.test.growMe.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import com.google.gson.annotations.SerializedName

/*@Serializable
data class Rating (
    @SerialName("rate"  ) var rate  : Double? = null,
    @SerialName("count" ) var count : Int?    = null
)*/

data class Rating (

    @SerializedName("rate"  ) var rate  : Double? = null,
    @SerializedName("count" ) var count : Int?    = null

)