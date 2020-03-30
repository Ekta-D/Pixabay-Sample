package com.android.payback.myapplication.repo

import com.android.payback.myapplication.model.ImageModel
import com.pixabay.repo.db.ImagesDAO
import javax.inject.Inject

class DBRepo @Inject
constructor(private val imageDAO: ImagesDAO) {

    fun insertAll(images: List<ImageModel>?) {
        if (images != null)
            imageDAO.insertAll(images)
    }
    fun search(word: String) = imageDAO.getImagesBySearchWord(word)
}