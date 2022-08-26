package com.rigo.nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rigo.nycschools.api.DataLayer
import com.rigo.nycschools.databinding.ActivityMainBinding
import com.rigo.nycschools.viewmodels.MainViewModel
import com.rigo.nycschools.views.SchoolsViewAdapter

class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private var mainLayoutManager: RecyclerView.LayoutManager? = null
    private var mainAdapter: SchoolsViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainLayoutManager = LinearLayoutManager(this)
        binding.schoolsView.layoutManager = mainLayoutManager
        mainAdapter = SchoolsViewAdapter()
        binding.schoolsView.adapter = mainAdapter

        viewModel.refreshSchools() //refresh NYC school data by calling api
        viewModel.schoolsLiveData.observe(this) { success -> //observe the above call
            if(success) {
                mainAdapter?.addAllSchools(DataLayer.schools)
            }
            else{
                Toast.makeText(this@MainActivity, "Failed to get schools data!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.refreshScores() // refresh score data by calling api
    }
}