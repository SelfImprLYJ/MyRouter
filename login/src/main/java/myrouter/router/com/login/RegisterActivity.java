package myrouter.router.com.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import myrouter.router.com.anotations.BindPath;

@BindPath("register/register")
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
