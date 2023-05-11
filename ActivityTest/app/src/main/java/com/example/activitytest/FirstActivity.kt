package com.example.activitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.baidu.android.pushservice.PushConstants
import com.baidu.android.pushservice.PushManager

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)

        var button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            Toast.makeText(this, "click the button", Toast.LENGTH_LONG).show()
//            startPush()
        }
    }

    private fun startPush () {
        PushManager.startWork(
            applicationContext,
            PushConstants.LOGIN_TYPE_API_KEY, "lYPBgyCoWoOK1GigC5DNoXnLSCRzRGp8");
    }

}