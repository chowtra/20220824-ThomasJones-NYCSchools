package com.rigo.nycschools.models

import com.squareup.moshi.Json

/**
 * define SAT score model
 */

data class Score(
    val dbn: String,
    @Json(name="school_name")
    val schoolName: String,
    @Json(name="num_of_sat_test_takers")
    val numOfSatTestTakers: String,
    @Json(name="sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String,
    @Json(name="sat_math_avg_score")
    val satMathAvgScore: String,
    @Json(name="sat_writing_avg_score")
    val satWritingAvgScore: String
)