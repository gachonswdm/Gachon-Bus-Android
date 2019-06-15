package io.github.gachonswdm.gachonbus.fragment

/*
    @name SettingFragment
    @author Minjae Seon
    @date 2019.06.16
 */


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.gachonswdm.gachonbus.R

class SettingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        return view
    }
}