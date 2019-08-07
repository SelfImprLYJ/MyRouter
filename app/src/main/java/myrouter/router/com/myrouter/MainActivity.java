package myrouter.router.com.myrouter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;
import myrouter.router.com.anotations.BindPath;
import myrouter.router.com.arouter.ARouter;

@BindPath("main/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> classes = getClasses(this, "myrouter.router.com.myrouter");
    }

    public void jumpActiviy(View view) {
        ARouter.getInstance().jumpActivity("login/login",null);
    }

    private List<String> getClasses(Context mContext, String packageName) {
        ArrayList classes = new ArrayList<>();
        try {
            String packageCodePath = mContext.getPackageCodePath();
            DexFile df = new DexFile(packageCodePath);
            Enumeration<String> entries = df.entries();
            String regExp = "^" + packageName + ".\\w+$";
            while (entries.hasMoreElements()){
                String name = entries.nextElement();
                classes.add(name);
                Log.d("MainActivity","MainActivity: " + name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
