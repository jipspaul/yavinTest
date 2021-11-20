package fr.jnvui.yavintest

import android.app.Application
import android.text.Editable
import android.view.View
import fr.jnvui.yavintest.dao.TicketsDAO
import fr.jnvui.yavintest.models.Ticket
import fr.jnvui.yavintest.module.appModule
import fr.jnvui.yavintest.module.databaseModule
import org.jetbrains.anko.doAsync
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //koin
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule, databaseModule)
        }

        //initial data on database
        val dao: TicketsDAO by inject()
        //TODO create db file
        doAsync {
            dao.insertAll(Ticket("1", "Single Journey ticket", "1,60£",0))
            dao.insertAll(Ticket("2", "One day ticket", "12,40£",0))
            dao.insertAll(Ticket("3", "One week ticket", "47,90£",0))
        }
    }

    fun View.hide() {
        this.visibility = View.GONE
    }

    fun View.show() {
        this.visibility = View.VISIBLE
    }

}