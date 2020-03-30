package com.pixabay.repo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.payback.myapplication.model.ImageModel

@Dao
interface ImagesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: ImageModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(images: List<ImageModel>)

    @androidx.room.Query("SELECT * FROM images WHERE searchWord = :word")
    fun getImagesBySearchWord(word: String): List<ImageModel>


    @androidx.room.Query("SELECT * FROM images ")
    fun getAll():  List<ImageModel>


    @androidx.room.Query("DELETE FROM images ")
    fun removeAll()


}