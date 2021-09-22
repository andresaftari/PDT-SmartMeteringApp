package org.smartmetering.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.smartmetering.client.databinding.ActivitySplashBinding
import org.smartmetering.client.ui.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                    startActivity(Intent(baseContext, MainActivity::class.java))
                    finish()
                } catch (ex: Exception) {
                    Log.d("FailedSplash", ex.localizedMessage!!)
                }
            }
        }.start()
    }
}