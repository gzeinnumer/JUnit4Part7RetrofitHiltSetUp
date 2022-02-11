package com.gzeinnumer.junit4part7retrofithiltsetup.data.remote.responses

//todo 2
data class ImageResponse (
    val hist: List<ImageResult>,
    val total: Int,
    val totalHits: Int
)