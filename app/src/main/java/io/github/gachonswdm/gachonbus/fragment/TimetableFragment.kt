package io.github.gachonswdm.gachonbus.fragment

/*
    @name TimelineFragment
    @author Minjae Seon
    @date 2019.06.16
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import io.github.gachonswdm.gachonbus.R

class TimetableFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_timetable, container, false)

        val webView: WebView = view.findViewById(R.id.timetable_webview)
        webView.loadUrl("file:///android_asset/www/timeline.html")
        return view
    }
}