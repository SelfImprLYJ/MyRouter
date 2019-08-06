package myrouter.router.com.myrouter;

import android.app.Application;

import myrouter.router.com.arouter.ARouter;

/**
 * @author liyongjian
 * @date 2019/8/6.
 * Description：
 */
public class LiApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.getInstance().init(this);
    }
}
