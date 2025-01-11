package com.test.growMe.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.growMe.data.CartProduct
import com.test.growMe.data.Product

@Database(entities = [Product::class, CartProduct::class], version = 1)
@TypeConverters(ProductConverter::class, CartConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
}
