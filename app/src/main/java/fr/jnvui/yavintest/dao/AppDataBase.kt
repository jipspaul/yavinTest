package fr.jnvui.yavintest.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.jnvui.yavintest.models.Ticket

@Database(entities = [Ticket::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ticketDao(): TicketsDAO
}