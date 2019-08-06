package myrouter.router.com.member;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import myrouter.router.com.anotations.BindPath;

@BindPath("member/member")
public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
    }
}
