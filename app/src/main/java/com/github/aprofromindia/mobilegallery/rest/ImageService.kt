package com.github.aprofromindia.mobilegallery.rest

import com.github.aprofromindia.mobilegallery.main.ImagesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ImageService {

    @GET("/svc/a/237089773")
    suspend fun getImages(): Response<ImagesResponse>
}