package com.project.extracurricularapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerClass: Spinner = findViewById(R.id.spinnerClass)
        val spinnerSchedule: Spinner = findViewById(R.id.spinnerSchedule)

        val adapterClass = ArrayAdapter.createFromResource(
            this,
            R.array.class_options,
            android.R.layout.simple_spinner_item
        )

        val adapterSchedule = ArrayAdapter.createFromResource(
            this,
            R.array.schedule_options,
            android.R.layout.simple_spinner_item
        )

        adapterClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterSchedule.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerClass.adapter = adapterClass
        spinnerSchedule.adapter = adapterSchedule

        val inputDate: TextInputEditText = findViewById(R.id.inputDate)
        inputDate.setOnClickListener {
            showDatePickerDialog(inputDate)
        }

        val buttonSave: Button = findViewById(R.id.button)

        val checkBox1: CheckBox = findViewById(R.id.checkBox1)
        val checkBox2: CheckBox = findViewById(R.id.checkBox2)
        val checkBox3: CheckBox = findViewById(R.id.checkBox3)
        val checkBox4: CheckBox = findViewById(R.id.checkBox4)
        val checkBox5: CheckBox = findViewById(R.id.checkBox5)
        val checkBox6: CheckBox = findViewById(R.id.checkBox6)

        buttonSave.setOnClickListener {
            val name = findViewById<TextInputEditText>(R.id.inputName).text.toString()
            val nis = findViewById<TextInputEditText>(R.id.inputNis).text.toString()
            val selectedClass = findViewById<Spinner>(R.id.spinnerClass).selectedItem.toString()
            val birthDate = findViewById<TextInputEditText>(R.id.inputDate).text.toString()
            val selectedRadioButtonId = findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId
            val gender = if (selectedRadioButtonId != -1) {
                findViewById<RadioButton>(selectedRadioButtonId).text.toString()
            } else {
                "Jenis Kelamin Belum Dipilih"
            }
            val extracurriculars = mutableListOf<String>()
            if (checkBox1.isChecked) extracurriculars.add(getString(R.string.basketLabel))
            if (checkBox2.isChecked) extracurriculars.add(getString(R.string.footballLabel))
            if (checkBox3.isChecked) extracurriculars.add(getString(R.string.swimmingLabel))
            if (checkBox4.isChecked) extracurriculars.add(getString(R.string.chessLabel))
            if (checkBox5.isChecked) extracurriculars.add(getString(R.string.debateLabel))
            if (checkBox6.isChecked) extracurriculars.add(getString(R.string.dramaLabel))
            val selectedSchedule = findViewById<Spinner>(R.id.spinnerSchedule).selectedItem.toString()

            if (name.isEmpty() || nis.isEmpty() || birthDate.isEmpty() || gender.isEmpty() || extracurriculars.isEmpty()) {
                Toast.makeText(this, "Harap lengkapi semua bidang terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("nis", nis)
                    putExtra("selectedClass", selectedClass)
                    putExtra("birthDate", birthDate)
                    putExtra("gender", gender)
                    putExtra("extracurriculars", extracurriculars.joinToString(", "))
                    putExtra("selectedSchedule", selectedSchedule)
                }
                startActivity(intent)
            }
        }
    }

    private fun showDatePickerDialog(inputDate: TextInputEditText) {
        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            inputDate.setText(dateFormat.format(calendar.time))
        }

        DatePickerDialog(
            this,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}
