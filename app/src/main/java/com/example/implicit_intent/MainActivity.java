package com.example.implicit_intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText web_text;
    private EditText location_text;
    private EditText share_text;
    private EditText newText;

    private Button web_btn;
    private Button location_btn;
    private Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web_text = findViewById(R.id.website_text);
        web_btn= findViewById(R.id.website_btn);
        share_text = findViewById(R.id.text_share);
        share_btn = findViewById(R.id.btn_share);
        location_text = findViewById(R.id.location_text);
        location_btn = findViewById(R.id.location_btn);
        Intent intent = getIntent();
        Uri uri =  intent.getData();
        if(uri != null){
            String url = uri.toString();
            if(!url.startsWith("http")){
                url = "http://" +url;
            }
            Uri webpage = Uri.parse(url);
            Intent intent2 = new Intent(Intent.ACTION_VIEW, webpage);
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"No application to handle this request", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity","intent cannot be implemented");
            }
        }


    }

    public void goToWebsite(View view) {
        String url = web_text.getText().toString();
        if(!url.startsWith("http")){
            url = "http://" +url;
        }
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"No application to handle this request", Toast.LENGTH_SHORT).show();
            Log.d("MainActivity","intent cannot be implemented");
        }

    }

    public void openLocation(View view) {
        String location =location_text.getText().toString();
        Uri address = Uri.parse("geo:0,0?q=" + location);
        Intent intent = new Intent(Intent.ACTION_VIEW, address);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"No application to handle this request", Toast.LENGTH_SHORT).show();
            Log.d("MainActivity","intent cannot be implemented");
        }
    }

    public void shareText(View view) {
        String text = share_text.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(text)
                .startChooser();
    }
}
