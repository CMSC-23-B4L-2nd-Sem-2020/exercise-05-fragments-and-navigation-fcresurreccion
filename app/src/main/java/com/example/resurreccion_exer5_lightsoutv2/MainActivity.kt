package com.example.resurreccion_exer5_lightsoutv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.resurreccion_exer5_lightsoutv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // override onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create a binding variable
        // and set the layout for the activity
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
}
