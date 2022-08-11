package com.example.washingpanda;

import static androidx.browser.customtabs.CustomTabsIntent.COLOR_SCHEME_SYSTEM;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    Button customChromeTabBtn;


    // url for loading in custom chrome tab
    String url = "https://www.washingpanda.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // initializing button for opening custom chrome tabs.
        customChromeTabBtn = findViewById(R.id.idBtnCustomChromeTab);
        customChromeTabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // initializing object for custom chrome tabs.
                CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();
                customIntent.setShowTitle(false);
                customIntent.setColorScheme(CustomTabsIntent.COLOR_SCHEME_DARK);

                // below line is setting toolbar color
                // for our custom chrome tab.
                //customIntent.setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.purple_200));

                // we are calling below method after
                // setting our toolbar color.
                openCustomTab(MainActivity.this, customIntent.build(), Uri.parse(url));
            }
        });
    }

    public static void openCustomTab(Activity activity, CustomTabsIntent customTabsIntent, Uri uri) {
        // package name is the default package
        // for our custom chrome tab
        String packageName = "com.android.chrome";
        if (packageName != null) {

            // we are checking if the package name is not null
            // if package name is not null then we are calling
            // that custom chrome tab with intent by passing its
            // package name.
            customTabsIntent.intent.setPackage(packageName);

            // in that custom tab intent we are passing
            // our url which we have to browse.
            customTabsIntent.launchUrl(activity, uri);
        } else {
            // if the custom tabs fails to load then we are simply
            // redirecting our user to users device default browser.
            activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }
}
