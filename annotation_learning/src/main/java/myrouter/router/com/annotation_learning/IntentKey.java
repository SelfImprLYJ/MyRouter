package myrouter.router.com.annotation_learning;

import android.support.annotation.Nullable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liyongjian
 * @date 2019-08-06.
 * Description：
 */

//注解有三大基本属性
//    1.Retention 作用域
//    2.Target 标示目标
//    3.Value 常量数据值

//    Retention的取值：源码级别，jar类级别，运行时级别
//    public enum RetentionPolicy {
//        SOURCE,
//        CLASS,
//        RUNTIME
//    }
//    第一个只作用于编译期，打包之后就没有了。比如@Override
//    第二个作用于jar包类，打包之后还存在，如Android的@Nullable，方便编译期跨包做lint检查
//    第三个作用于运行期，在运行时可通过反射取得这个注解的信息，如retrofit的@GET注解
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface IntentKey {
//    支持8中基本数据类型，String，Class，Annotation及子类，枚举 以及它们的数组
    String[] names();
    String value();
}
