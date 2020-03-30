package com.android.payback.myapplication.model


import java.io.Serializable

data class ResponseModel(
    val hits: List<ImageModel>,
    val total: Int,
    val totalHits: Int
): Serializable