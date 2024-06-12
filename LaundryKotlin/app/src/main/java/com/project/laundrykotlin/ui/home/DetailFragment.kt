package com.project.laundrykotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.laundrykotlin.R
import com.project.laundrykotlin.ui.database.AppDatabase

class DetailFragment : Fragment() {

    private lateinit var database: AppDatabase
    private lateinit var adapter: FormDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase.getDatabase(requireContext())
        adapter = FormDataAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvFormData)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        database.formDataDao().getAll().observe(viewLifecycleOwner, { formDataList ->
            adapter.setData(formDataList)
        })
    }

}