package com.example.kwonjoanne.spotifymaster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class MainActivity extends AppCompatActivity {

    // Request code will be used to verify if result comes from the login activity. Can be set to any integer.
    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "spotifymaster://callback";
    private static String CLIENT_ID = "838225cfa48d4d779612dbb0f815fcba";

    private Button loginButton;
    private Intent resultIntent;

    /*
        TextView textView = (TextView)findViewById(R.id.apptitle);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
        textView.setTypeface(typeface);
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


//        SpotifyApi.initialize(this, new ClientConfig.Builder()
//                .setClientId("clientId")
//                .setClientSecret("clientSecret")
//                .setRedirectUri("redirectUri")
//                .build());

        TextView textView = (TextView)findViewById(R.id.apptitle);

        textView.setText(Html.fromHtml(getString(R.string.spotifyMaster)));

        loginButton = (Button)findViewById(R.id.login_Button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("FMLFMLFMLFMLFML");

                AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

                builder.setScopes(new String[]{"streaming"});

                builder.setShowDialog(true);

                AuthenticationRequest request = builder.build();

                resultIntent = new Intent(MainActivity.this, MainActivity.class);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                //this doesn't work for some reason....
//                AuthenticationClient.openLoginActivity(MainActivity.this, REQUEST_CODE, request);

                //but this does!!


                AuthenticationClient.openLoginInBrowser(MainActivity.this, request);

                onNewIntent(resultIntent);

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        System.out.println("SUCCESS!!!!");

        Uri uri = intent.getData();
        if (uri != null) {
            AuthenticationResponse response = AuthenticationResponse.fromUri(uri);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
//                    System.out.println("SUCCESS!!!!");
                    startActivity(new Intent(MainActivity.this, TestActivity.class));
                    // Handle successful response
//                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//
//        // Check if result comes from the correct activity
//        if (requestCode == REQUEST_CODE) {
//            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
//
//            switch (response.getType()) {
//                // Response was successful and contains auth token
//                case TOKEN:
//                    //go to list activity?
//                    System.out.println("Success!!!!!");
//                    // Handle successful response
//                    break;
//
//                // Auth flow returned an error
//                case ERROR:
//                    //THIS WORKS!!!! just have to figure out what to do here.
//                    // Handle error response
//                    startActivity(new Intent(MainActivity.this, TestActivity.class));
//
////                    break;
//
//                // Most likely auth flow was cancelled
//                default:
//                    // Handle other cases
//            }
//        }
//    }

}
