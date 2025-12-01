package com.nti.lab_7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(v -> scanCode());
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Наведите камеру на QR код");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    private final androidx.activity.result.ActivityResultLauncher<ScanOptions> barLauncher =
            registerForActivityResult(new ScanContract(), result -> {
                if (result.getContents() != null) {
                    String scannedText = result.getContents();
                    checkQrCode(scannedText);
                }
            });

    private void checkQrCode(String qrText) {
        String cleanText = qrText.replaceAll("\\s+", "").toLowerCase();
        switch (cleanText) {
            case "дом":
            case "house":
                startResultActivity("Дом", R.drawable.house, "Это уютный дом");
                break;
            case "машина":
            case "car":
                startResultActivity("Машина", R.drawable.car, "Это быстрая машина");
                break;
            case "дерево":
            case "tree":
                startResultActivity("Дерево", R.drawable.tree, "Это большое дерево");
                break;
            default:
                startResultActivity(qrText, R.drawable.question_mark, "Неизвестный текст");
                break;
        }
    }

    private void startResultActivity(String title, int imageRes, String description) {
        Intent intent = new Intent(ActivityMain.this, ActivityResult.class);
        intent.putExtra("title", title);
        intent.putExtra("imageRes", imageRes);
        intent.putExtra("description", description);
        startActivity(intent);
    }
}
