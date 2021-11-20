package fr.jnvui.yavintest.ui.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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