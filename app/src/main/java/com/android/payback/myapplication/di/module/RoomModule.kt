package com.android.payback.myapplication.di.module

import android.content.Context
import androidx.room.Room
import com.android.payback.myapplication.repo.db.DBHelper
import com.android.payback.myapplication.utils.Cons
import com.pixabay.repo.db.ImagesDAO
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class RoomModule {

    @Singleton
    @Provides
    fun getImagesDAO(context: Context, @Named("dbName") dbName: String): DBHelper {

        return Room.databaseBuilder(
            context,
            DBHelper::class.java, dbName
        )
            //.allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getDAO(imgDB: DBHelper): ImagesDAO {
        return imgDB.getImagesDAO()
    }

    @Singleton
    @Provides
    @Named("dbName")
    fun provideDbName(): String {
        return Cons.DB_NAME
    }


}