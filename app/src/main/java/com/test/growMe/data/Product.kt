package com.test.growMe.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import com.google.gson.annotations.SerializedName

/*@Serializable
data class Products (
    @SerialName("id"          ) var id          : Int?    = null,
    @SerialName("title"       ) var title       : String? = null,
    @SerialName("price"       ) var price       : Double? = null,
    @SerialName("description" ) var description : String? = null,
    @SerialName("category"    ) var category    : String? = null,
    @SerialName("image"       ) var image       : String? = null,
    @SerialName("rating"      ) var rating      : Rating? = Rating()
)*/




data class Products (

    @SerializedName("id"          ) var id          : Int?    = null,
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("price"       ) var price       : Double? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("category"    ) var category    : String? = null,
    @SerializedName("image"       ) var image       : String? = null,
    @SerializedName("rating"      ) var rating      : Rating? = Rating()

)