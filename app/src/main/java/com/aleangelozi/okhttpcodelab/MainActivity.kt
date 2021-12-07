package com.aleangelozi.okhttpcodelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;

import  okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mTextViewResult = findViewById<TextView>(R.id.text_view_result)

        val client = OkHttpClient()

        val url = "https://reqres.in/api/users?page=2"

        val request: Request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val myResponse = response.body?.string()

                    runOnUiThread { mTextViewResult.text = myResponse }
                }
            }
        })
    }
}