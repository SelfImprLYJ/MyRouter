package myrouter.router.com.myrouter;

import myrouter.router.com.arouter.ARouter;
import myrouter.router.com.arouter.IRouter;

/**
 * @author liyongjian
 * @date 2019-08-06.
 * Description：
 */
public class ActivityUtilDemo implements IRouter {
    @Override
    public void putActivity() {
        ARouter.getInstance().putActivity("",MainActivity.class);
    }
}
