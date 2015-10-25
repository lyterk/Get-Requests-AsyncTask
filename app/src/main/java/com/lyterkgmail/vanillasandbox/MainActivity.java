package com.lyterkgmail.vanillasandbox;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

public class MainActivity
        extends Activity
        implements ProcessListener {

    private static final String TAG = "lyterkgmail.Main";

    private ProcessListener mProcessListener;
    private Button getBtn;
    private TextView getTV;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getTV = (TextView) findViewById(R.id.site_text);
        getBtn = (Button) findViewById(R.id.get_button);

        mProcessListener = new ProcessListener() {
            @Override
            public void ProcessingDone(String res) {
                getTV.setText(res);
            }
        };
    }

    public void onClick(View v) throws IOException {
        switch(v.getId()) {
            case R.id.get_button:
                getRequest();
                break;
        }
    }

//     Starts new AsyncTask to send GET request to site. Runs in background.
    private void getRequest() throws IOException {
        GetRequest getRequest = new GetRequest(mProcessListener);
        URL url = getRequest.urlCreate("http://www.httpbin.org/");
        if (isNetworkAvailable(this)) {
            Toast.makeText(this, "Network connected.", Toast.LENGTH_LONG).show();
            getRequest.execute(url);
        } else {
            Toast.makeText(this, "Network not connected.", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isNetworkAvailable(Context ctx) { // This could be passed a context to take it out of Main.
        ConnectivityManager conMgr =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected() ||
                conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) return true;
        return false;
    }

//    Manifestation of interface.
    @Override
    public void ProcessingDone(String result) {
        //    All side-effectful stuff goes here. Insulates Main thread from AsyncTask duties.
    }
}
