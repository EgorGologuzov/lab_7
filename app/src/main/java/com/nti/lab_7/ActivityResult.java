package com.nti.lab_7;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvTitle = findViewById(R.id.tvTitle);
        ImageView ivImage = findViewById(R.id.ivImage);
        TextView tvDescription = findViewById(R.id.tvDescription);

        String title = getIntent().getStringExtra("title");
        int imageRes = getIntent().getIntExtra("imageRes", R.drawable.ic_launcher_background);
        String description = getIntent().getStringExtra("description");

        tvTitle.setText(title);
        ivImage.setImageResource(imageRes);
        tvDescription.setText(description);
    }
}
