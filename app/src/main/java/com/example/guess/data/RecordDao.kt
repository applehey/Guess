package com.example.guess.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //同樣資料insert進去就replace資料
    fun insert(record: Record)

    @Query("select * from record")
    fun getAll() : List<Record>
}