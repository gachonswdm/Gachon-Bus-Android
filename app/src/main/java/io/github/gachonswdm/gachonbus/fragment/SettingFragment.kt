package io.github.gachonswdm.gachonbus.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import io.github.gachonswdm.gachonbus.R
/*
    @name SettingFragment
    @author Minjae Seon
    @date 2019.06.16
 */


class SettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settingpreference, rootKey)
    }

}