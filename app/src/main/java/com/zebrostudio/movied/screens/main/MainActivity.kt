package com.example.movied

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zebrostudio.movied.screens.main.MainContract
import com.zebrostudio.movied.screens.main.MainContract.MainPresenter
import com.zebrostudio.movied.screens.main.MainContract.MainView
import org.koin.android.ext.android.inject
import org.koin.core.context.KoinContextHandler.get

class MainActivity : AppCompatActivity(), MainView {

    private val presenter: MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.handleViewCreated(this)
    }
}
