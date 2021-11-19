package fr.jnvui.yavintest

import android.app.Application
import fr.jnvui.yavintest.module.appModule
import fr.jnvui.yavintest.module.databaseModule
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
    }
}