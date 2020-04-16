package com.zebrostudio.movied.screens.main

import androidx.lifecycle.LifecycleOwner

interface MainContract{
    interface MainView{

    }

    interface MainPresenter{
        fun handleViewCreated(lifecycleOwner: LifecycleOwner)
    }
}