package com.ven.balu.data;

import android.content.Context;
import android.content.SharedPreferences;

// Singleton Pattern
public class SharedPreferenceManager {
    private static SharedPreferenceManager mInstance;
    private static Context ctx;

    //test
    private static final String _NAME = "testSharedPreference";
    private static final String _LOGIN = "loginPref";

    private SharedPreferenceManager(Context context) {
        ctx = context;
    }

    // synchronized: đồng bộ hóa các phương thức và khối mã để tránh tình trạng xung đột giữa các luồng thực thi,
    // chỉ có một luồng thực thi được phép truy cập vào phương thức hoặc khối mã đó tại một thời điểm.
    public static synchronized SharedPreferenceManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreferenceManager(context);
        }
        return mInstance;
    }
    public void saveLoggedUserID(Long id) {
        SharedPreferences.Editor editor = getSharedPreferences(_LOGIN).edit();
        editor.putLong("loggedID", id);
        editor.apply();
    }
    public void saveName(String data) {
        // Tạo đối tượng SharedPreferences với tên "_NAME"
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(_NAME, Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences(_NAME);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", data);
        editor.apply();
    }

    public String getName() {
        return getSharedPreferences(_NAME).getString("name", "null");
    }

    private SharedPreferences getSharedPreferences(String prefName) {
        return ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }
}
