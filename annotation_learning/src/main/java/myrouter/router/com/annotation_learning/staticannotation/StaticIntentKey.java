package myrouter.router.com.annotation_learning.staticannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liyongjian
 * @date 2019-08-06.
 * Description：
 */
//三种作用域都可以标识静态注解
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface StaticIntentKey {
    String value();
}
