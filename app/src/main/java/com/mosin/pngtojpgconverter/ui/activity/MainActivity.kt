package com.mosin.pngtojpgconverter.ui.activity

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.mosin.pngtojpgconverter.App
import com.mosin.pngtojpgconverter.IBackClickListener
import com.mosin.pngtojpgconverter.R
import com.mosin.pngtojpgconverter.databinding.ActivityMainBinding
import com.mosin.pngtojpgconverter.mvp.presenter.MainPresenter
import com.mosin.pngtojpgconverter.mvp.view.MainView
import com.mosin.pngtojpgconverter.ui.navigation.AndroidScreens
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    val navigator = AppNavigator(this, R.id.container)
    private var ui: ActivityMainBinding? = null

    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is IBackClickListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }
}
