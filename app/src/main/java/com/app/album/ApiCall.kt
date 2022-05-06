package com.app.album

import retrofit2.Response
import retrofit2.http.GET

interface ApiCall {
    @GET("/photos")
    suspend fun getAllPhotos() : Response<Array<DataModel>>
}