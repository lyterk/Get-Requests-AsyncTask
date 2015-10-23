package com.lyterkgmail.vanillasandbox;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GET extends AsyncTask<URL, String, String> {

    private static final String TAG = "getTask";
    private Boolean connected = false;
    private String result;
    private HttpURLConnection mHttpURLConnection;
    private Context ctx;
    private SiteData mSiteData;

    public GET (Context ctx) {
        this.ctx = ctx;
    }

    public SiteData getSiteData() {
        return mSiteData;
    }

    public URL urlCreate(String strUrl) throws IOException {
        return new URL(strUrl);
    }

    private String inputStreamToString(InputStream in) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return total.toString();
    }

    @Override
    protected String doInBackground(URL... urls) {
        try {
            mHttpURLConnection = (HttpURLConnection) urls[0].openConnection();
            InputStream in = new BufferedInputStream(mHttpURLConnection.getInputStream());
            result = inputStreamToString(in);
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG, e.toString());
        } catch (IOException e) {
            Log.d(TAG, e.toString());
        } finally {
            mHttpURLConnection.disconnect();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mSiteData = new SiteData(s);
        Toast.makeText(ctx, "OnPostExecute success!", Toast.LENGTH_LONG).show();
    }

/*  Fucking. Java.
    private boolean isNetworkAvailable(Context ctx) { // This could be passed a context to take it out of Main.
        ConnectivityManager conMgr =
                    (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = conMgr.getActiveNetworkInfo();
        if(conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected() ||
                conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()){
            return true;
        }
        return false;
    }
    */
}