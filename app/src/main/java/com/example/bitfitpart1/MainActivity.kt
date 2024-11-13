package com.example.bitfitpart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val storedRecords = mutableListOf<DisplayExercise>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch{
            (application as ExerciseApplication).db.exerciseDao().getAll().collect { databaseList ->
                databaseList.map {entity ->
                    DisplayExercise(
                        entity.name,
                        entity.duration
                    )
                }.also { mappedList ->
                    storedRecords.clear()
                    storedRecords.addAll(mappedList)
                }

            }
        }

        val fragmentManager: FragmentManager = supportFragmentManager

        // define fragments
        val listFragment: Fragment = ListFragment()
        val summaryFragment: Fragment = SummaryFragment(storedRecords)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.listTab -> fragment = listFragment
                R.id.summaryTab -> fragment = summaryFragment
            }
            fragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.listTab

        val launchBtn = findViewById<Button>(R.id.addItemBtn)
        launchBtn.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            this.startActivity(intent)
        }
    }
}