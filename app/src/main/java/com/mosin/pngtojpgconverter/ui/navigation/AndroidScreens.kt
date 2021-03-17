package com.mosin.pngtojpgconverter.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.mosin.pngtojpgconverter.mvp.navigation.IScreens
import com.mosin.pngtojpgconverter.ui.fragment.FragmentConverter

class AndroidScreens : IScreens {
    override fun converter() = FragmentScreen { FragmentConverter.newInstance() }
}