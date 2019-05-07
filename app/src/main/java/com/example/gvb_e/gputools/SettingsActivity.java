package com.example.gvb_e.gputools;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static android.content.ContentValues.TAG;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {

    public static int OVERLAY_PERMISSION_REQ_CODE = 1000;
    public static final String KEY_PREF_SYNC_CONN = "pref_syncConnectionType";
//    SettingsActivity gpuinfo = new SettingsActivity();

    //SharedPreferences pref = getSharedPreferences("user_data", MODE_PRIVATE);
    //String key = pref.getString("example_switch", "null");

    //public CharSequence getSwitchTextOff();

//    public void stopService() {
//        // クリック時の処理
//        stopService(new Intent(SettingsActivity.this, LayerService.class));
//    }

    @TargetApi(Build.VERSION_CODES.M)
    public void checkPermission() {
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                Log.d("debug","SYSTEM_ALERT_WINDOW permission not granted...");
                // SYSTEM_ALERT_WINDOW permission not granted...
                // nothing to do !
            }
        }
    }
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        Process _process2 = null;
        DataOutputStream _outputStream2;

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            try {
                _process2 = Runtime.getRuntime().exec("su");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                android.util.Log.e("yuki", "yuki key=" + stringValue);

                String stval = stringValue;

                switch (stval) {
                    case "50":
                        String[] commandaudioroot0 = {"find /sys/class/drm/card0/device/hwmon -name pwm1_enable | while read -r file; do echo 1 > ${file}; done"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandaudioroot0
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        String[] commandaudioroot1 = {"find /sys/class/drm/card0/device/hwmon -name pwm1 | while read -r file; do echo 128 > ${file}; done"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandaudioroot1
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "75":
                        String[] commandaudioroot2 = {"find /sys/class/drm/card0/device/hwmon -name pwm1_enable | while read -r file; do echo 1 > ${file}; done"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandaudioroot2
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        String[] commandaudioroot3 = {"find /sys/class/drm/card0/device/hwmon -name pwm1 | while read -r file; do echo 191 > ${file}; done"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandaudioroot3
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "100":
                        String[] commandaudioroot4 = {"find /sys/class/drm/card0/device/hwmon -name pwm1_enable | while read -r file; do echo 1 > ${file}; done"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandaudioroot4
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        String[] commandaudioroot5 = {"find /sys/class/drm/card0/device/hwmon -name pwm1 | while read -r file; do echo 255 > ${file}; done"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandaudioroot5
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "auto":
                        String[] commandaudioroot6 = {"echo auto > /sys/class/drm/card0/device/power_dpm_force_performance_level"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandaudioroot6
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "high":
                        String[] commandaudioroot7 = {"echo high > /sys/class/drm/card0/device/power_dpm_force_performance_level"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandaudioroot7
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "low":
                        String[] commandaudioroot8 = {"echo low > /sys/class/drm/card0/device/power_dpm_force_performance_level"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandaudioroot8
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "defwm":
                        String[] commandwm0 = {"wm size reset"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandwm0
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    break;
                    case "3840":
                        String[] commandwm1 = {"wm size 3840x2160"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandwm1
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "2560":
                        String[] commandwm2 = {"wm size 2560x1440"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandwm2
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "1920":
                        String[] commandwm3 = {"wm size 1920x1080"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandwm3
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "1280":
                        String[] commandwm4 = {"wm size 1280x720"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandwm4
                                ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "hdmi":
                        String[] commandwm5 = {"setprop hal.audio.primary.hdmi 1"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandwm5
                        ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    case "oaudio":
                        String[] commandwm6 = {"setprop hal.audio.primary.hdmi 0"};
                        _outputStream2 = new DataOutputStream(_process2.getOutputStream());
                        for (String command : commandwm6
                        ) {
                            try {
                                _outputStream2.writeBytes(command + "\n");
                                _outputStream2.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    default:
                }

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }

    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkPermission();
        super.onCreate(savedInstanceState);
        setupActionBar();

//        try {
//            _process = Runtime.getRuntime().exec("su");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void startServiceinfo() {
        Intent intent = new Intent(SettingsActivity.this, LayerService.class);
        //Intent intent = new Intent(getActivity(), LayerService.class);
        // Serviceの開始
        // API26以上
        startForegroundService(intent);
//        SwitchPreference switchPref = new SwitchPreference(this);
//        android.util.Log.e("yuki", "yuki key=true" + switchPref.getSwitchTextOff());
        android.util.Log.e("yuki", "yuki key=startService");
        //Intent intent = new Intent(getActivity(), LayerService.class);
        //Intent intent = new Intent(getApplication(), LayerService.class);
        // Serviceの開始
        // API26以上
        //getActivity().startForegroundService(intent);
    }

    public void audioRootingon() {
//        try {
//            _process = Runtime.getRuntime().exec("su setprop hal.audio.primary.hdmi 0");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void audioRootingoff() {

    }

//        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
//                                              String key) {
//            if (key.equals(KEY_PREF_SYNC_CONN)) {
//                Preference connectionPref = findPreference(key);
//                // Set summary to be the user-description for the selected value
//                connectionPref.setSummary(sharedPreferences.getString(key, ""));
//            }
//        }
//    @Override
//    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
//                                         Preference preference) {
//        // key"follow_user"が返される
//        android.util.Log.e("yuki", "yuki key=" + preference.getKey());
//        return true;
//    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || DataSyncPreferenceFragment.class.getName().equals(fragmentName)
                || NotificationPreferenceFragment.class.getName().equals(fragmentName);
    }

    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {

//        Process _process = null;
//        DataOutputStream _outputStream = null;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);

//            try {
//                _process = Runtime.getRuntime().exec("su");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            setHasOptionsMenu(true);

            SwitchPreference vibrateSwitch = (SwitchPreference) findPreference("example_switch");

            if (vibrateSwitch != null) {
                vibrateSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object isVibrateOnObject) {
                        boolean isVibrateOn = (Boolean) isVibrateOnObject;
                        if (isVibrateOn) {
                            android.util.Log.e("yuki", "yuki key=Monitor Start");
                            //gpuinfo.startServiceinfo();
                            Intent intent = new Intent(getActivity(), LayerService.class);
                            // Serviceの開始
                            // API26以上
                            getActivity().startForegroundService(intent);
                        }else{
                            android.util.Log.e("yuki", "yuki key=Monitor Stop");
                            //gpuinfo.stopService();
                            getActivity().stopService(new Intent(getActivity(), LayerService.class));
                        }
                        return true;
                    }
                });
            }

//            SwitchPreference audioSwitch = (SwitchPreference) findPreference("audio_root");
//
//            if (audioSwitch != null) {
//                audioSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//                    @Override
//                    public boolean onPreferenceChange(Preference arg0, Object isVibrateOnObject) {
//                        boolean isVibrateOn = (Boolean) isVibrateOnObject;
//                        if (isVibrateOn) {
//                            android.util.Log.e("yuki", "yuki key=Audio True");
//                            //gpuinfo.audioRootingon();
//                            //String[] commandaudioroot = {"wm size 3840x2160"};
//                            String[] commandaudioroot = {"setprop hal.audio.primary.hdmi 0"};
//                            _outputStream = new DataOutputStream(_process.getOutputStream());
//                            for (String command : commandaudioroot
//                                    ) {
//                                try {
//                                    _outputStream.writeBytes(command + "\n");
//                                    _outputStream.flush();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                        }else{
//                            android.util.Log.e("yuki", "yuki key=Audeio False");
//                            //gpuinfo.audioRootingoff();
//                            String[] commandaudioroot = {"setprop hal.audio.primary.hdmi 1"};
//                            //String[] commandaudioroot = {"wm size reset"};
//                            _outputStream = new DataOutputStream(_process.getOutputStream());
//                            for (String command : commandaudioroot
//                                    ) {
//                                try {
//                                    _outputStream.writeBytes(command + "\n");
//                                    _outputStream.flush();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                        return true;
//                    }
//                });
//            }

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("example_text"));
            bindPreferenceSummaryToValue(findPreference("example_list"));
            bindPreferenceSummaryToValue(findPreference("gpupower_set"));
            bindPreferenceSummaryToValue(findPreference("display_set"));
            bindPreferenceSummaryToValue(findPreference("audio_root"));

            //switchPref.setChecked(true);

//            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//            String syncConnPref = sharedPref.getString(SettingsActivity.KEY_PREF_SYNC_CONN, "");

//            bindPreferenceSummaryToValue(findPreference("example_switch"));

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

//        @Override
//        public boolean onPreferenceTreeClick (PreferenceScreen preferenceScreen,
//                                              Preference preference)
//        {
//
//            switch (preference.getKey()){
//                case "example_switch":
//                    //Do your stuff
//                    android.util.Log.e("yuki", "yuki key=true");
//
//                    Intent intent = new Intent(getActivity(), LayerService.class);
//                    // Serviceの開始
//                    // API26以上
//                    getActivity().startForegroundService(intent);
//                    gpuinfo.startServiceinfo();
//                    break;
//            }
//            return super.onPreferenceTreeClick(preferenceScreen, preference);
//
//        }



    }

    /**
     * This fragment shows notification preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class NotificationPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_notification);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows data and sync preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class DataSyncPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_data_sync);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("sync_frequency"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
