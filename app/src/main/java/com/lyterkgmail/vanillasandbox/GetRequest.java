package com.lyterkgmail.vanillasandbox;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest extends AsyncTask<URL, String, String> {

    private static final String TAG = "getTask";
    private String result;
    private HttpURLConnection mHttpURLConnection;
    private ProcessListener mProcessListener;

    public GetRequest(ProcessListener mProcessListener) {
        this.mProcessListener = mProcessListener;
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
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mProcessListener.ProcessingDone(result);
    }
}