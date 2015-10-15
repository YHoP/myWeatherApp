package com.example.guest.myweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.io.IOException;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    String apiKey = "12cf5c054101992f485f24eceeb58e8e";
    double latitude = 45.5311331;
    double longitude = -122.6824974;
    String forecastURL = "https://api.forecast.io/forecast/"+ apiKey +"/"+ latitude +","+ longitude;

    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .url(forecastURL)
            .build();
    Call call = client.newCall(request);
    call.enqueue(new Callback() {
        @Override
        public void onFailure (Request request, IOException e){

        }

        @Override
        public void onResponse (Response response)throws IOException {
            try {
                // Response response = call.execute();
                Log.v(TAG, response.body().string());
                if (response.isSuccessful()) {

                } else {
                    alertUserAboutError();
                }
            } catch (IOException e) {
                // e.printStackTrace();
                Log.e(TAG, "Exception caught: ", e);
            }
        }

        });
        Log.d(TAG, "Main UI code is running!");
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

}