package com.project.laundrykotlin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.project.laundrykotlin.R
import com.project.laundrykotlin.ui.database.AppDatabase
import com.project.laundrykotlin.ui.database.FormData
import kotlinx.coroutines.launch

class InputFragment : Fragment() {

    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase.getDatabase(requireContext())

        val inputName = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.inputName)
        val inputNumber = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.inputNumber)
        val inputAddress = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.inputAddress)

        val buttonSave = view.findViewById<Button>(R.id.button)
        buttonSave.setOnClickListener {
            val name = inputName.text.toString()
            val number = inputNumber.text.toString()
            val address = inputAddress.text.toString()
            val formData = FormData(name = name, number = number, address = address)

            lifecycleScope.launch {
                database.formDataDao().insert(formData)
                Toast.makeText(requireContext(), "Success input data", Toast.LENGTH_SHORT).show()
                inputName.text = null
                inputNumber.text = null
                inputAddress.text = null
            }
        }
    }
}