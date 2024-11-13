package com.example.bitfitpart1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.Exercise
import com.example.bitfit.ExerciseEntity
import kotlinx.coroutines.launch

class ListFragment : Fragment() {
    private lateinit var rvExercises: RecyclerView
    private lateinit var addBtn: Button
    private var entries: MutableList<DisplayExercise> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        rvExercises = view.findViewById(R.id.rvExercises)

        // Create adapter passing in the list
        val adapter = ExercisesAdapter(view.context, entries)
        // Attach the adapter to the RecyclerView to populate items
        rvExercises.adapter = adapter
        // Set layout manager to position the items
        rvExercises.layoutManager = LinearLayoutManager(view.context)

        lifecycleScope.launch {
            (requireActivity().application as ExerciseApplication).db.exerciseDao()
                .getAll().collect { databaseList ->
                    databaseList.map { entity ->
                        ExerciseEntity(
                            entity.id,
                            entity.name,
                            entity.duration,
                        )
                    }.also { mappedList ->
                        val exerciseEntries = mappedList.map { exerciseEntity ->
                            DisplayExercise(
                                exerciseEntity.name,
                                exerciseEntity.duration,
                            )
                        }
                        entries.clear()
                        entries.addAll(exerciseEntries)
                        rvExercises.adapter?.notifyDataSetChanged()
                    }
                }
        }
        return view
    }

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}