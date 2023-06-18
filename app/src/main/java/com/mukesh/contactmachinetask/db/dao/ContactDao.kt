package com.mukesh.contactmachinetask.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto

@Dao
interface ContactDao {

    @Query("SELECT * FROM contactdto")
    fun getAllContacts(): List<ContactDto>?


    @Query("SELECT * FROM contactdto WHERE id=:id")
    fun getContactDetail(id: Int): ContactDto?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContact(contactDto: ContactDto?)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContacts(contactList: List<ContactDto>?)


    @Query("DELETE FROM contactdto")
    suspend fun deleteAllContacts()

}