package myrouter.router.com.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //注解作用域，类
@Retention(RetentionPolicy.CLASS) //注解生命周期 source源码期 class编译期  runtime运行期
public @interface BindPath {
    String value();
}
