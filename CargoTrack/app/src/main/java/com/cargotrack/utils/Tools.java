package com.cargotrack.utils;

import android.os.StrictMode;

public class Tools {
    public static void NetPolicy() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
