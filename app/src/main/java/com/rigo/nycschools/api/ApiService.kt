package com.rigo.nycschools.api

import com.rigo.nycschools.models.School
import com.rigo.nycschools.models.Score
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interfaces for api endpoints
 */

interface ApiService {

    @GET("s3k6-pzi2.json")
    suspend fun getSchools(): Response<List<School>>
    @GET("f9bf-2cp4.json")
    suspend fun getScores(): Response<List<Score>>
}