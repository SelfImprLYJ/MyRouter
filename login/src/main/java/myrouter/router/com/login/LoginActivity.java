package myrouter.router.com.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import myrouter.router.com.anotations.BindPath;
import myrouter.router.com.arouter.ARouter;

@BindPath("login/login")
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void jumpActiviy(View view) {
        ARouter.getInstance().jumpActivity("register/register",null);
    }
}
