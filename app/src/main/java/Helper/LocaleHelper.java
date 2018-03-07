package Helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceManager;

import java.util.Locale;

/**
 * Created by User on 3/3/2018.
 */

public class LocaleHelper {
    private static final String Selected_Language="Locale.Helper.Selected.Language";
    public static Context onAttach(Context context){
        String lan=getPersistentData(context, Locale.getDefault().getLanguage());
        return setLocale(context, lan);
    }

    public static Context onAttach(Context context, String defaultLanguage){
        String lan=getPersistentData(context, defaultLanguage);
        return setLocale(context, lan);
    }
    private static String getPersistentData(Context context, String language) {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(Selected_Language, language);
    }

    public static Context setLocale(Context context, String lan) {
        persist(context, lan);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            return updateResources(context, lan);
        return updateResourcesLegacy(context, lan);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String lan) {
        Locale locale=new Locale(lan);
        Locale.setDefault(locale);
        Configuration config=context.getResources().getConfiguration();
        config.setLocale(locale);
        config.setLayoutDirection(locale);
        return context.createConfigurationContext(config);
    }

    @SuppressWarnings("deprication")
    private static Context updateResourcesLegacy(Context context, String lan) {
        Locale locale=new Locale(lan);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config=resources.getConfiguration();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1)
            config.setLayoutDirection(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        return context;

    }
    private static void persist(Context context, String lan) {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(Selected_Language, lan);
        editor.apply();
    }
}
