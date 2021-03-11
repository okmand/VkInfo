package and.okm.vkinfo.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import and.okm.vkinfo.R;
import and.okm.vkinfo.constants.ExtraConstants;
import and.okm.vkinfo.models.ResponseVk;
import and.okm.vkinfo.models.UserInfo;
import and.okm.vkinfo.utils.JsonUtils;
import and.okm.vkinfo.utils.NetworkUtils;

import static and.okm.vkinfo.constants.LogConstants.*;
import static and.okm.vkinfo.utils.NetworkUtils.generateUrl;
import static and.okm.vkinfo.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText searchField;
    private Button searchButton;
    private TextView errorMessage;
    private ProgressBar loadingIndicator;

    class VkQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            loadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            String response;
            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                response = e.getMessage();
                Log.w(TAG, e.getMessage());
            }
            return response;
        }

        @Override
        protected void onPostExecute(String json) {
            ResponseVk response;

            if (null != json && !json.equals("")) {
                try {
                    response = JsonUtils.fromJson(json, ResponseVk.class);
                    if (!response.getUsersInfo().isEmpty()) {
                        UserInfo userInfo = response.getUsersInfo().get(0);

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(userInfo.getFirstName()).append(" ").append(userInfo.getLastName());
                        Log.d(TAG, stringBuilder.toString());

                        Intent intent = new Intent(MainActivity.this, ResultSearchActivity.class);
                        intent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());

                        byte[] imageBytes = NetworkUtils.downloadImage(userInfo.getPhotoMaxOrig());
                        intent.putExtra(ExtraConstants.EXTRA_PHOTO, imageBytes);

                        startActivity(intent);
                        hideError();
                    } else {
                        showError(ERROR_INVALID_USER_ID);
                    }
                } catch (IOException e) {
                    Log.e(TAG_ERROR, e.getMessage());
                    e.printStackTrace();
                    showError(ERROR_OOPS);
                }
            } else {
                showError(ERROR_OOPS);
                Log.w(TAG_WARNING, "null != json or !json.equals(\"\")");
            }
            loadingIndicator.setVisibility(View.INVISIBLE);
        }
    }

    private void hideError() {
        errorMessage.setVisibility(View.INVISIBLE);
    }

    private void showError(String error) {
        errorMessage.setText(error);
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.et_search_field);
        searchButton = findViewById(R.id.b_search_vk);
        loadingIndicator = findViewById(R.id.pb_loading_indicator);
        errorMessage = findViewById(R.id.tv_error_message);

        searchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            String id = searchField.getText().toString();
            if (null != id && !id.equals("")) {
                URL url = generateUrl(id);
                new VkQueryTask().execute(url);
            }
        } catch (MalformedURLException e) {
            Log.e(TAG_ERROR, e.getMessage());
        }
    }
}
