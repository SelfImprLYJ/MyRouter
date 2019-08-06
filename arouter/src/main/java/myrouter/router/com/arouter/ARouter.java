package myrouter.router.com.arouter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

/**
 * @author liyongjian
 * @date 2019/8/4.
 * Description：
 */
public class ARouter {

    //装载左右Activity的类对象容器
    private Map<String, Class<? extends Activity>> mActivityMap;
    private Context mContext;

    private ARouter() {
    }

    public static ARouter getInstance(){
        return ARouter.InnerClass.INSTANCE;
    }

    public void init(Application application){
        mContext = application;
        mActivityMap = new HashMap<>();
        List<String> classNames = getClassName("com.myrouter.util");
        if (classNames != null && classNames.size() > 0) {
            for (String className : classNames){
                try {
                    Class<?> aClass = Class.forName(className);
                    if (IRouter.class.isAssignableFrom(aClass)){
                        IRouter iRouter = (IRouter) aClass.newInstance();
                        iRouter.putActivity(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private List<String> getClassName(String packageName) {
        //创建一个class集合对象
        List<String> classNames = new ArrayList<>();
        String path = null;
        try {
            path = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(),0).sourceDir;
            //根据apk的完整路径获取到编译后的dex文件
            DexFile dexFile = new DexFile(path);
            //获取编译后的dex文件中的所有class
            Enumeration<String> entries = dexFile.entries();
            //进行遍历
            while (entries.hasMoreElements()){
                //通过遍历所有的class包名
                String name = entries.nextElement();
                //判断类的包名是否符合
                if (name.contains(packageName)){
                    //如果符合，就添加到集合中
                    classNames.add(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classNames;
    }

    public void putActivity(String path,Class <? extends Activity> clazz){
        if (mActivityMap == null) {
            return;
        }
        if (clazz != null && !TextUtils.isEmpty(path)) {
            mActivityMap.put(path,clazz);
        }
    }

    public void jumpActivity(String path, Bundle bundle){
        if (mContext == null || mActivityMap == null) {
            throw new RuntimeException("Arouter 没有初始化");
        }
        Class<? extends Activity> aClass = mActivityMap.get(path);
        if (aClass == null) {
            return;
        }
        Intent intent = new Intent(mContext,aClass);
        intent.putExtras(bundle);
        mContext.startActivity(intent);

    }

    private static class InnerClass{
        private static final ARouter INSTANCE = new ARouter();
    }


}
