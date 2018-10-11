package com.arfeenkhan.settingdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;


public class SettingsFragment extends PreferenceFragment {


    public static final String PREF_SMS_LIMIT = "sms_delete_limit";
    public static final String PREF_MMS_LIMIT = "mms_delete_limit";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                if (key.equals(PREF_SMS_LIMIT)) {
                    Preference smsPref = findPreference(key);
                    smsPref.setSummary(sharedPreferences.getString(key, "") + "messsage per conversation");
                }

                if (key.equals(PREF_MMS_LIMIT)) {
                    Preference mmsPref = findPreference(key);
                    mmsPref.setSummary(sharedPreferences.getString(key, "") + "messsage per conversation");
                }

            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);

        Preference smsPref = findPreference(PREF_SMS_LIMIT);
        smsPref.setSummary(getPreferenceScreen().getSharedPreferences().getString(PREF_SMS_LIMIT, "") + "messages per conversation");

        Preference mmsPref = findPreference(PREF_MMS_LIMIT);
        mmsPref.setSummary(getPreferenceScreen().getSharedPreferences().getString(PREF_MMS_LIMIT, "") + "messages per conversation");
    }


    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }
}
