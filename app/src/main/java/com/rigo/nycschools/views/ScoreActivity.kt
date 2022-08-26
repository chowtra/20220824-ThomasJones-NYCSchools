package com.rigo.nycschools.views

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rigo.nycschools.MainActivity
import com.rigo.nycschools.api.DataLayer
import com.rigo.nycschools.databinding.ActivityMainBinding
import com.rigo.nycschools.databinding.ActivityScoreBinding
import com.rigo.nycschools.viewmodels.MainViewModel

class ScoreActivity: AppCompatActivity() {

    private lateinit var binding: ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbn = intent.getStringExtra("dbn")
        binding.dbn.text = dbn
        DataLayer.getScoreByDbn(dbn).let {
            if (it != null) {
                binding.name.text = it.schoolName
                binding.takers.text = "Num of SAT Test Takers: " + it.numOfSatTestTakers
                binding.readingScore.text = "SAT Critical Reading Avg. Score: " + it.satCriticalReadingAvgScore
                binding.mathScore.text = "SAT Math Avg. Score: " + it.satMathAvgScore
                binding.writingScore.text = "SAT Writing Avg. Score: " + it.satWritingAvgScore
            }

        }
    }
}