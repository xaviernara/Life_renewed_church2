package com.example.life_renewed.api

import com.example.life_renewed.model.AnnouncementsItem
import retrofit2.Response
import retrofit2.http.GET

interface LifeRenewApi {

    @GET("announcements")
    suspend fun getLifeRenew() : Response<AnnouncementsItem>
}