package com.app.album

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {
    @Insert
    fun insertData(data: DataEntity)

    @Query("SELECT * FROM data")
    fun getAllData() : List<DataEntity>
}