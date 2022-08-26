package com.rigo.nycschools.api

import com.rigo.nycschools.models.School
import com.rigo.nycschools.models.Score

/**
 * Represents data used in app fetching remote data.
 */

object DataLayer {

    var schools: List<School> = emptyList()
    var scores: List<Score> = emptyList()
    suspend fun fetchSchools(): Boolean {
        schools = emptyList()
        val request = NetworkLayer.apiClient.getSchools()
        if(request.isSuccessful) {
            request.body()!!.also { schools = it }
            return true
        }
        return false
    }
    suspend fun fetchScores(): Boolean {
        scores = emptyList()
        val request = NetworkLayer.apiClient.getScores()
        if(request.isSuccessful) {
            request.body()!!.also { scores = it }
            return true
        }
        return false
    }

    /**
     * Return a record matched by dbn in SAT score list
     */

    fun getScoreByDbn(dbn: String?): Score? {
        if(dbn == null) return null
        val candidates = scores.filter {
            it.dbn == dbn
        }
        if (candidates.isEmpty()) return null
        else return candidates[0]
    }
}