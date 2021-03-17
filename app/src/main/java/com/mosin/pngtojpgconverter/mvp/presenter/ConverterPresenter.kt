package com.mosin.pngtojpgconverter.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.mosin.pngtojpgconverter.mvp.view.ConverterView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class ConverterPresenter(private val scheduler: Scheduler, private val router: Router) :
    MvpPresenter<ConverterView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}