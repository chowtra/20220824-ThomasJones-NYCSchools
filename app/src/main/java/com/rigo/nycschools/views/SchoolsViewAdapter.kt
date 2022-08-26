package com.rigo.nycschools.views

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rigo.nycschools.R
import com.rigo.nycschools.api.DataLayer
import com.rigo.nycschools.models.School
import com.rigo.nycschools.models.Score

class SchoolsViewAdapter: RecyclerView.Adapter<SchoolsViewAdapter.SchoolCardHolder>() {
    private var schools: ArrayList<School> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolCardHolder {
        val card = LayoutInflater.from(parent.context).inflate(R.layout.school_card, parent, false)
        return SchoolCardHolder(card)
    }

    override fun onBindViewHolder(holder: SchoolCardHolder, position: Int) {
        holder.itemDbn.text = schools[position].dbn
        holder.itemName.text = schools[position].schoolName
        holder.itemOverview.text = schools[position].overviewParagraph
    }

    override fun getItemCount(): Int {
        return schools.size
    }

    fun addAllSchools(schools: List<School>) {
        this.schools.clear()
        this.schools.addAll(schools)
        notifyDataSetChanged()
    }

    inner class SchoolCardHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemDbn: TextView
        var itemName: TextView
        var itemOverview: TextView

        init {
            itemDbn = itemView.findViewById(R.id.dbn)
            itemName = itemView.findViewById(R.id.name)
            itemOverview = itemView.findViewById(R.id.overview)

            itemView.setOnClickListener{
                val position: Int = absoluteAdapterPosition
                val score = DataLayer.getScoreByDbn(schools[position].dbn)
                if(score != null) {
                    val intent = Intent(itemView.context, ScoreActivity::class.java)
                    intent.putExtra("dbn", schools[position].dbn)
                    itemView.context.startActivity(intent)
                }
                else {
                    Toast.makeText(itemView.context, "No SAT data!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}