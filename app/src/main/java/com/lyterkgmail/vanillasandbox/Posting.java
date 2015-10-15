package com.lyterkgmail.vanillasandbox;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Posting {

    private static final String requestBinUrl= "http://requestb.in/1me0jin1";
    private URL url;

    url = new URL(requestBinUrl);

    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
}
