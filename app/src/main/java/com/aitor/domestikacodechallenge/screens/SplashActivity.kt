package com.aitor.domestikacodechallenge.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aitor.domestikacodechallenge.screens.courselist.CourseListActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // I'm doing nothing here but usually I would ask for permissions or start loading data
        // in background
        startActivity(Intent(this@SplashActivity, CourseListActivity::class.java))
    }
}
