package com.example.bitfitpart1

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.Exercise

private const val TAG = "ExercisesAdapter"
class ExercisesAdapter(private val context: Context, private val exercises: List<DisplayExercise>):
    RecyclerView.Adapter<ExercisesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.exercise_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val movie = exercises[position]
        holder.bind(movie)
    }

    override fun getItemCount() = exercises.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.name)
        private val tvDur = itemView.findViewById<TextView>(R.id.duration)

        fun bind(exercise: DisplayExercise) {
            tvName.text = exercise.name
            tvDur.text = exercise.duration.toString()
        }
    }
}
