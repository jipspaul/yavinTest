package fr.jnvui.yavintest.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.jnvui.yavintest.models.Ticket

@Dao
interface TicketsDAO {
    /**
     * Add ticket in the DB
     * **/
    @Insert
    fun insertAll(vararg tickets: Ticket)

    /**
     * Remove ticket from DB
     */
    @Delete
    fun delete(ticket: Ticket)

    /**
     * Modify ticket in DB
     * you should use the id of the ticket you want to update
     */
    @Update
    fun update(ticket: Ticket)

    /**
     * get all entry of DB
     */
    @Query("SELECT * FROM ticket")
    fun getAll(): LiveData<List<Ticket>>

    /**
     * Get ticket by id
     */
    @Query("SELECT ticket_price, ticket_type,id,ticket_counter FROM ticket WHERE id = :ticketId LIMIT 1")
    fun loadSingle(ticketId: String): LiveData<Ticket>

    /**
     * Get ticket from id with no LiveData
     */
    @Query("SELECT ticket_price, ticket_type,id,ticket_counter FROM ticket WHERE id = :ticketId LIMIT 1")
    fun loadSingle2(ticketId: String): Ticket

}