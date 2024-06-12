package com.project.laundrykotlin.ui.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FormDataDao {
    @Insert
    suspend fun insert(formData: FormData)

    @Query("SELECT * FROM form_data_table")
    fun getAll(): LiveData<List<FormData>>
}