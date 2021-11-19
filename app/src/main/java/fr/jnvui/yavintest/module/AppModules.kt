package fr.jnvui.yavintest.module

import fr.jnvui.yavintest.ui.home.HomeViewModel
import fr.jnvui.yavintest.usecases.TicketUseCase
import org.koin.dsl.module

val appModules = module {

    single { TicketUseCase() }
    factory { HomeViewModel(get()) }
}