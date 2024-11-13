package com.example.bitfitpart1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bitfit.ExerciseEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddActivity: AppCompatActivity() {
    private lateinit var etExercise: EditText
    private lateinit var etDur: EditText
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_page)
        etExercise= findViewById(R.id.etExercise)
        etDur = findViewById(R.id.etDur)
        submitBtn = findViewById(R.id.recordBtn)

        submitBtn.setOnClickListener { addItem() }
    }

    private fun addItem() {
        val exerciseName = etExercise.text.toString()
        val dur = etDur.text.toString().toDouble()

        let {
            lifecycleScope.launch(IO) {
                val list = ArrayList<ExerciseEntity>()
                list.add(ExerciseEntity(name = exerciseName, duration = dur))
                (application as ExerciseApplication).db.exerciseDao().insertAll(list)
            }
        }

        finish()
    }
}