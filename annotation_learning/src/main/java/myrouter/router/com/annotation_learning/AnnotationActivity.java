package myrouter.router.com.annotation_learning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AnnotationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
    }

    public void jump(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("key_name","dynamic_inject");
        startActivity(intent);
    }
}
