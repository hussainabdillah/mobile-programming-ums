package com.project.extracurricularapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val inputNameTextView = findViewById<TextView>(R.id.inputName)
        val inputNisTextView = findViewById<TextView>(R.id.inputNis)
        val inputClassTextView = findViewById<TextView>(R.id.inputClass)
        val inputDateTextView = findViewById<TextView>(R.id.inputDate)
        val inputGenderTextView = findViewById<TextView>(R.id.inputGender)
        val inputExtracurricularTextView = findViewById<TextView>(R.id.inputExtracurricular)
        val inputScheduleTextView = findViewById<TextView>(R.id.inputSchedule)

        val name = intent.getStringExtra("name")
        val nis = intent.getStringExtra("nis")
        val className = intent.getStringExtra("selectedClass")
        val birthDate = intent.getStringExtra("birthDate")
        val gender = intent.getStringExtra("gender")
        val extracurricular = intent.getStringExtra("extracurriculars")
        val schedule = intent.getStringExtra("selectedSchedule")

        inputNameTextView.text = name
        inputNisTextView.text = nis
        inputClassTextView.text = className
        inputDateTextView.text = birthDate
        inputGenderTextView.text = gender
        inputExtracurricularTextView.text = extracurricular
        inputScheduleTextView.text = schedule
    }
}
