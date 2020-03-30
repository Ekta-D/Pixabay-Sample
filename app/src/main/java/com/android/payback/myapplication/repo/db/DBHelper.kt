package com.android.payback.myapplication.repo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.payback.myapplication.model.ImageModel
import com.android.payback.myapplication.utils.Cons.Companion.DB_VERSION
import com.pixabay.repo.db.ImagesDAO

@Database(entities = [ImageModel::class], version = DB_VERSION)
abstract class DBHelper : RoomDatabase() {
    abstract fun getImagesDAO(): ImagesDAO
}