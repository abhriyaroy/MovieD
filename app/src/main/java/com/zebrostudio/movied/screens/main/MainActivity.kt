package com.example.movied

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zebrostudio.movied.screens.main.MainContract.MainPresenter
import com.zebrostudio.movied.screens.main.MainContract.MainView

class MainActivity : AppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
