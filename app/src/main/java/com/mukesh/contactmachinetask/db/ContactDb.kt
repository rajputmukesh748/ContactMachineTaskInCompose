package com.mukesh.contactmachinetask.db

import androidx.room.RoomDatabase
import com.mukesh.contactmachinetask.data.remote.dto.ContactDto
import com.mukesh.contactmachinetask.data.remote.dto.MessagesDto
import com.mukesh.contactmachinetask.db.dao.ContactDao
import com.mukesh.contactmachinetask.db.dao.MessagesDao


@androidx.room.Database(
    entities = [ContactDto::class, MessagesDto::class],
    version = 5,
    exportSchema = true
)
abstract class ContactDb : RoomDatabase() {

    abstract fun contactsDao() : ContactDao

    abstract fun messagesDao(): MessagesDao

}