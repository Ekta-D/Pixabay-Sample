package com.android.payback.myapplication.repo

import com.android.payback.myapplication.utils.Cons
import javax.inject.Inject


class RemoteRepo @Inject
constructor(private val repoService: RestService) {

    suspend fun searchImages(word: String) =
        repoService.searchImage(
            key = Cons.API_KEY,
            image_type = Cons.IMAGES_TYPE,
            q = word
        )
}