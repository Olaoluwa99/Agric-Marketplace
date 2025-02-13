package com.test.growMe.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.growMe.data.CartRating
import com.test.growMe.data.Rating

class ProductConverter {
    @TypeConverter
    fun fromRating(rating: Rating): String {
        return Gson().toJson(rating)
    }

    @TypeConverter
    fun toRating(ratingString: String): Rating {
        val type = object : TypeToken<Rating>() {}.type
        return Gson().fromJson(ratingString, type)
    }
}

class CartConverter {
    @TypeConverter
    fun fromRating(rating: CartRating): String {
        return Gson().toJson(rating)
    }

    @TypeConverter
    fun toRating(ratingString: String): CartRating {
        val type = object : TypeToken<CartRating>() {}.type
        return Gson().fromJson(ratingString, type)
    }
}