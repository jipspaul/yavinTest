package fr.jnvui.yavintest.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.jnvui.yavintest.models.Ticket

@Dao
interface TicketsDAO {
    @Insert
    fun insertAll(vararg tickets: Ticket)

    @Delete
    fun delete(ticket: Ticket)

    @Update
    fun update(ticket: Ticket)

    @Query("SELECT * FROM ticket")
    fun getAll(): LiveData<List<Ticket>>

    @Query("SELECT ticket_price, ticket_type,id,ticket_counter FROM ticket WHERE id = :ticketId LIMIT 1")
    fun loadSingle(ticketId: String): LiveData<Ticket>

}