package myrouter.router.com.myrouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import myrouter.router.com.anotations.BindPath;
import myrouter.router.com.arouter.ARouter;

@BindPath("main/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpActiviy(View view) {
        ARouter.getInstance().jumpActivity("login/login",null);
    }
}
