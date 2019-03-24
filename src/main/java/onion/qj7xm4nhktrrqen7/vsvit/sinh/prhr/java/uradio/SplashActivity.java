package onion.qj7xm4nhktrrqen7.vsvit.sinh.prhr.java.uradio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, UradioActivity.class);
        startActivity(intent);
        finish();
    }
}
