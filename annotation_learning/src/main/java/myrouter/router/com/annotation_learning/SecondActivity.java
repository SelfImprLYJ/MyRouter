package myrouter.router.com.annotation_learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import myrouter.router.com.annotation_learning.dynamicannotation.DynamicInject;
import myrouter.router.com.annotation_learning.dynamicannotation.DynamicIntentKey;

public class SecondActivity extends AppCompatActivity {

    @DynamicIntentKey("key_name")
    private String dynamicName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        DynamicInject.inject(this);
        Toast.makeText(this,dynamicName,Toast.LENGTH_LONG).show();
    }
}
