package com.lyterkgmail.vanillasandbox;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String TAG = "lyterkgmail.Main";

    boolean connected = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    private boolean isNetworkAvailable() { // This could be passed a context to take it out of Main.
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            boolean connected = activeNetworkInfo != null &&
                    activeNetworkInfo.isConnectedOrConnecting();
            return connected;
        } catch (Exception e) {
            Log.v(TAG, "connectivity " + e.toString());
        }
        return connected;
    }
}
