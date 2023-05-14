package com.ven.balu.data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.ven.balu.model.Customer;

// Singleton Pattern
public class SharedPreferenceManager {
    private static SharedPreferenceManager mInstance;
    private static Context ctx;

    // PREF NAME
    private static final String LOGIN_PREF = "loginPref";
    // FLAG in storage
    private static final String FLAG_LOGGED = "flag_logged";

    private SharedPreferenceManager(Context context) {
        ctx = context.getApplicationContext();
    }

    // synchronized: đồng bộ hóa các phương thức và khối mã để tránh tình trạng xung đột giữa các luồng thực thi,
    // chỉ có một luồng thực thi được phép truy cập vào phương thức hoặc khối mã đó tại một thời điểm.
    public static synchronized SharedPreferenceManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreferenceManager(context);
        }
        return mInstance;
    }
    public void saveCustomerId(Integer customerId) {
        SharedPreferences.Editor editor = getPrivateSharedPreferences(LOGIN_PREF).edit();
        editor.putInt("customerId", customerId);
        editor.putBoolean(FLAG_LOGGED, true);
        editor.apply();
    }
    public Integer getCustomerId() {
        return getPrivateSharedPreferences(LOGIN_PREF).getInt("customerId", -1);
    }
    public void saveCustomer(Customer customer) {
        SharedPreferences.Editor editor = getPrivateSharedPreferences(LOGIN_PREF).edit();

        Gson gson = new Gson();
        String customer_json = gson.toJson(customer);

        editor.putString("customer", customer_json);
        editor.apply();

    }
    public Customer getCustomer() {
        String json = getPrivateSharedPreferences(LOGIN_PREF).getString("customer", null);
        if (json != null) {
            Gson gson = new Gson();
            Customer customer = gson.fromJson(json, Customer.class);
            return customer;
        }
        return null;
    }
    public boolean isLogin() {
        return getPrivateSharedPreferences(LOGIN_PREF).getBoolean(FLAG_LOGGED, false);
    }

    public void logout() {
        SharedPreferences.Editor editor = getPrivateSharedPreferences(LOGIN_PREF).edit();
        editor.clear();
        editor.apply();
    }

    private SharedPreferences getPrivateSharedPreferences(String prefName) {
        return ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }
}

/* getUser
    String json = sharedPreferences.getString("user", null);
if (json != null) {
    Gson gson = new Gson();
    User user = gson.fromJson(json, User.class);
}
     */