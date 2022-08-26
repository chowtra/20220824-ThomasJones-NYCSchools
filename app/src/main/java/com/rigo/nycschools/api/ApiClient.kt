package com.rigo.nycschools.api

import com.rigo.nycschools.models.School
import com.rigo.nycschools.models.Score
import retrofit2.Response

/**
 * Implementation for api endpoints
 */

class ApiClient (
    private val apiService: ApiService
) {
    suspend fun getSchools(): Response<List<School>> {
        return apiService.getSchools()
    }
    suspend fun getScores(): Response<List<Score>> {
        return apiService.getScores()
    }
}