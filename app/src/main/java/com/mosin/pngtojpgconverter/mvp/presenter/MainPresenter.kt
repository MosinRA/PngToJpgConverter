package com.mosin.pngtojpgconverter.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.mosin.pngtojpgconverter.mvp.navigation.IScreens
import com.mosin.pngtojpgconverter.mvp.view.MainView
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.converter())
    }

    fun backPressed() {
        router.exit()
    }
}