package com.lyterkgmail.vanillasandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends Activity {

    private static final String TAG = "lyterkgmail.Main";

    boolean connected = false;
    private String gotten;
    private SiteData mSiteData;

    private Button getBtn;
    private Button updateBtn;
    private TextView getTV;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getBtn = (Button) findViewById(R.id.get_button);
        updateBtn = (Button) findViewById(R.id.update_button);
        getTV = (TextView) findViewById(R.id.site_text);

    }

    private void onClick(View v) throws IOException {
        switch(v.getId()) {
            case R.id.get_button:
                getting();
            case R.id.update_button:
                updateUi();
        }
    }

    private void updateUi() {
        if (mSiteData != null) {
            String str = mSiteData.getResult();
            getTV.setText(str);
        } else {
            getTV.setText("No site data returned");
        }
    }

    private SiteData getting() throws IOException {
        GET get = new GET(this);
        URL url = get.urlCreate("http://www.httpbin.org/");
        get.execute(url);
        return get.getSiteData();
    }
}
