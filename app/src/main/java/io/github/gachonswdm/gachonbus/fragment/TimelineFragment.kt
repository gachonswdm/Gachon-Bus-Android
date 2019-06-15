package io.github.gachonswdm.gachonbus.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.gachonswdm.gachonbus.R

/*
    @name TimelineFragment
    @author Minjae Seon
    @date 2019.06.16
 */


class TimelineFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_timeline, container, false)
        return view
    }
}