package and.okm.vkinfo.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import and.okm.vkinfo.R;
import and.okm.vkinfo.constants.ExtraConstants;

public class ResultSearchActivity extends AppCompatActivity {

    private TextView resultSearch;
    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);

        resultSearch = findViewById(R.id.tv_result_search);
        photo = findViewById(R.id.iv_photo);

        showInfo();
    }

    private void showInfo() {
        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String textResultSearch = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            byte[] imageBytes = intentThatStartedThisActivity.getByteArrayExtra(ExtraConstants.EXTRA_PHOTO);
            Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes , 0, imageBytes.length);

            resultSearch.setText(textResultSearch);
            photo.setImageBitmap(bmp);
        }
    }
}