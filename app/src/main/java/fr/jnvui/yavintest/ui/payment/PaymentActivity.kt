package fr.jnvui.yavintest.ui.payment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.jnvui.yavintest.R

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PaymentFragment.newInstance())
                .commitNow()
        }
    }
}