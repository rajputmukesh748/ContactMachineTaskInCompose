package com.mukesh.contactmachinetask.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mukesh.contactmachinetask.data.remote.dto.MessagesDto

@Dao
interface MessagesDao {

    @Query("SELECT * FROM messagesdto ORDER BY createdAt DESC")
    fun getAllMessages(): List<MessagesDto>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMessage(messagesDto: MessagesDto)

}