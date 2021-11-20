package fr.jnvui.yavintest.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Ticket(
    @PrimaryKey() var id: String,
    @ColumnInfo(name = "ticket_type") var ticketType: String,
    @ColumnInfo(name = "ticket_price") var ticketPrice: String,
    @ColumnInfo(name = "ticket_counter") var ticketCartCounter: Int
)