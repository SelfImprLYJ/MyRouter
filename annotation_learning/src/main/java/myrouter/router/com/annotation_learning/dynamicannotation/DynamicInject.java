package myrouter.router.com.annotation_learning.dynamicannotation;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author liyongjian
 * @date 2019-08-06.
 * Description：
 */
public class DynamicInject {

    public static void inject(Activity activity) {
        if (activity == null) {
            throw new RuntimeException("动态注入未注册");
        }

        Intent intent = activity.getIntent();

        //利用反射效率比较低  这里先看利用动态注解简化代码
        //反射
        Field[] declaredFields = activity.getClass().getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0) {
            for (Field field : declaredFields) {
                //判断成员变量是否加了DynamicIntentKey注解
                if (field.isAnnotationPresent(DynamicIntentKey.class)) {
                    //获取注解的key
                    DynamicIntentKey annotation = field.getAnnotation(DynamicIntentKey.class);
                    String intentKey = annotation.value();
                    //根据可以去获取intent的值
                    Serializable serializableExtra = intent.getSerializableExtra(intentKey);

                    if (serializableExtra == null) {
                        if (field.getType().isAssignableFrom(String.class)) {
                            serializableExtra = "";
                        }
                    }

//                  给成员变量插入值
                    try {
                        boolean accessible = field.isAccessible();
                        field.setAccessible(true);
                        field.set(activity, serializableExtra);
                        field.setAccessible(accessible);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }
}
