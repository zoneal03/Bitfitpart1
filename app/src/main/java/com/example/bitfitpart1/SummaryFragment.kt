package com.example.bitfitpart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.math.BigDecimal
import java.math.RoundingMode

class SummaryFragment(private val storedRecords: List<DisplayExercise>) : Fragment() {
    private lateinit var etExercise: EditText
    private lateinit var etDur: EditText
    private lateinit var submitBtn: Button

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        // total exercises, total time spent, avg time spent
        val totalExerView = view.findViewById(R.id.TotalExer) as TextView
        val totalTimeView = view.findViewById(R.id.TimeSpent) as TextView
        val avgTimeView = view.findViewById(R.id.Average) as TextView
        var totalCount = storedRecords.size
        var totalTimeCount = 0.0

        storedRecords.forEach { exercise ->
            if (exercise.duration != null) {
                totalTimeCount += exercise.duration
            }
        }
        // update view
        if (totalCount != 0) {
            var avgTime = BigDecimal(totalTimeCount/totalCount).setScale(2, RoundingMode.HALF_UP).toDouble()
            totalExerView.text = "$totalCount Workouts"
            totalTimeView.text = "$totalTimeCount Hours"
            avgTimeView.text = "$avgTime hours/workout"
        }
        return view
//
//        etExercise= view.findViewById(R.id.etExercise)
//        etDur = view.findViewById(R.id.etDur)
//        submitBtn = view.findViewById(R.id.recordBtn)
//
//        submitBtn.setOnClickListener {
//            val exerciseName = etExercise.text.toString()
//            val dur = etDur.text.toString()
//
//            let {
//                lifecycleScope.launch(Dispatchers.IO) {
//                    val list = ArrayList<ExerciseEntity>()
//                    list.add(ExerciseEntity(name = exerciseName, duration = dur))
//                    (requireActivity().application as ExerciseApplication).db.exerciseDao().insertAll(list)
//                }
//            }
//        }
//
    }

//    companion object {
//        fun newInstance(): AddFragment {
//            return AddFragment()
//        }
//    }
}