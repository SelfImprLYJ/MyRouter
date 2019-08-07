package myrouter.router.com.arouter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dalvik.system.DexFile;

/**
 * @author liyongjian
 * @date 2019/8/4.
 * Description：
 */
public class ARouter {

    private static final String ROUTES_PACKAGE_NAME = "com.myrouter.util";
    //装载左右Activity的类对象容器
    private Map<String, Class<? extends Activity>> mActivityMap;
    private Context mContext;

    private ARouter() {
    }

    public static ARouter getInstance() {
        return ARouter.InnerClass.INSTANCE;
    }

    public void init(Application application) {
        mContext = application;
        mActivityMap = new HashMap<>();

        try {
            Set<String> classNames = ClassUtils.getFileNameByPackageName(application, ROUTES_PACKAGE_NAME);
            if (classNames != null && classNames.size() > 0) {
                for (String className : classNames) {
                    Class<?> aClass = Class.forName(className);
                    if (IRouter.class.isAssignableFrom(aClass)) {
                        IRouter iRouter = (IRouter) aClass.newInstance();
                        iRouter.putActivity();
                    }
                }
            }
        } catch (Exception e) {

        }

//        List<String> classNames = getClassName(ROUTES_PACKAGE_NAME);
//        classNames = getClasses(mContext,ROUTES_PACKAGE_NAME);


    }

    public void putActivity(String path, Class<? extends Activity> clazz) {

        if (mActivityMap == null) {
            return;
        }
        if (clazz != null && !TextUtils.isEmpty(path)) {
            mActivityMap.put(path, clazz);
        }
    }

    public void jumpActivity(String path, Bundle bundle) {
        if (mContext == null || mActivityMap == null) {
            throw new RuntimeException("Arouter 没有初始化");
        }
        Class<? extends Activity> aClass = mActivityMap.get(path);
        if (aClass == null) {
            return;
        }
        Intent intent = new Intent(mContext, aClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);

    }

    private static class InnerClass {
        private static final ARouter INSTANCE = new ARouter();
    }


}
