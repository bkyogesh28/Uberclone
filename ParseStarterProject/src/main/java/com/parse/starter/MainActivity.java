/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;



public class MainActivity extends AppCompatActivity {
  Switch usertype;

public void rideractivity(){
  if(ParseUser.getCurrentUser().get("riderOrDriver")=="rider"){
    Intent intent=new Intent(getApplicationContext(),RiderActivity.class);
    startActivity(intent);
  }
}
  @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
  public void start(View view) {
    usertype = (Switch) findViewById(R.id.rider);
    Log.i("Inf0", String.valueOf(usertype.isChecked()));
    String user = "rider";
    if (usertype.isChecked()) {
      user = "driver";
    }
    ParseUser.getCurrentUser().put("riderOrDriver", user);
    Log.i("info", "chosen as" + " " + user);

  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getSupportActionBar().hide();
    if (ParseUser.getCurrentUser() == null) {
      ParseAnonymousUtils.logIn(new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {
          if (e == null) {
            Log.i("info", "LOGED IN ANONYMOUSLY");
          } else {
            Log.i("info", "LOGIN FAILED");
          }
        }
      });
    } else {
      if (ParseUser.getCurrentUser().get("riderOrDriver") != null) {
        Log.i("info", "chosen as" + " " + ParseUser.getCurrentUser().get("riderOrDriver"));

      }

    }
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }
}