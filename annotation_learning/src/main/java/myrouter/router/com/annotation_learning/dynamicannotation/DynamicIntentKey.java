package myrouter.router.com.annotation_learning.dynamicannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liyongjian
 * @date 2019-08-06.
 * Description：
 */

//动态注解就是运行时注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DynamicIntentKey {
    String value();
}
