/*
 * Copyright (C) 2012 CyanogenMod
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings;

import android.app.ActivityManagerNative;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.RemoteException;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.util.Log;

public class SystemSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    private static final String TAG = "SystemSettings";

    private static final String KEY_BATTERY_PERCENTAGE = "battery_percentage";
    private static final String KEY_VOLUME_MUSIC_CONTROLS = "volume_music_controls";

    private CheckBoxPreference mBatteryPercentage;
    private CheckBoxPreference mVolumeMusicControls;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	
        addPreferencesFromResource(R.xml.system_settings);

        PreferenceScreen prefSet = getPreferenceScreen();

        mBatteryPercentage = (CheckBoxPreference) prefSet.findPreference(KEY_BATTERY_PERCENTAGE);
        mBatteryPercentage.setChecked((Settings.System.getInt(getContentResolver(),
            Settings.System.STATUS_BAR_BATTERY, 0) == 1));

        mVolumeMusicControls = (CheckBoxPreference) prefSet.findPreference(KEY_VOLUME_MUSIC_CONTROLS);
        mVolumeMusicControls.setChecked((Settings.System.getInt(getContentResolver(),
            Settings.System.VOLBTN_MUSIC_CONTROLS, 0) == 1));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean value;

        if (preference == mBatteryPercentage) {
            Settings.System.putInt(getContentResolver(), Settings.System.STATUS_BAR_BATTERY,
                    mBatteryPercentage.isChecked() ? 1 : 0);
        } else if (preference == mVolumeMusicControls) {
            Settings.System.putInt(getContentResolver(), Settings.System.VOLBTN_MUSIC_CONTROLS,
                    mVolumeMusicControls.isChecked() ? 1 : 0);
        } else {
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }

        return true;
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        return true;
    }
}
