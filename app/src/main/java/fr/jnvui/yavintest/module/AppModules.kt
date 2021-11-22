package fr.jnvui.yavintest.module

import android.app.Application
import androidx.room.Room
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.dao.AppDatabase
import fr.jnvui.yavintest.dao.TicketsDAO
import fr.jnvui.yavintest.network.RequestTransactionBody
import fr.jnvui.yavintest.usecases.TicketUseCase
import fr.jnvui.yavintest.usecases.TransactionsUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { RequestTransactionBody(androidContext().resources.getString(R.string.yavin_token)) }
    single { TicketUseCase(get()) }
    single { TransactionsUseCase(get()) }
}

val databaseModule = module {
    fun provideDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java, "ticketdb"
        ).build()
    }

    fun provideDao(dataBase: AppDatabase): TicketsDAO {
        return dataBase.ticketDao()
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}