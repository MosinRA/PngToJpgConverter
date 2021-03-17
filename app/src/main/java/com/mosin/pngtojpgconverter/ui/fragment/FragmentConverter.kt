package com.mosin.pngtojpgconverter.ui.fragment

import com.mosin.pngtojpgconverter.App
import com.mosin.pngtojpgconverter.IBackClickListener
import com.mosin.pngtojpgconverter.mvp.presenter.ConverterPresenter
import com.mosin.pngtojpgconverter.mvp.view.ConverterView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FragmentConverter : MvpAppCompatFragment(), ConverterView, IBackClickListener {

    companion object {
        fun newInstance() = FragmentConverter()
    }

    private val presenter by moxyPresenter {
        ConverterPresenter(AndroidSchedulers.mainThread(), App.instance.router)}

    override fun init() {
        TODO("Not yet implemented")
    }

    override fun setImg() {
        TODO("Not yet implemented")
    }

    override fun setText() {
        TODO("Not yet implemented")
    }

    override fun backPressed() = presenter.backPressed()
}