package myrouter.router.com.annotation_complier;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import myrouter.router.com.anotations.BindPath;

/**
 * @author liyongjian
 * @date 2019-08-06.
 * Description：
 */

//编写一个自定义注解的四条步骤：
//
//1.创建一个java library
//2.编写一个IntentProcessor继承与Processor的抽象类AbstractProcessor
//
@AutoService(Processor.class) //注册注解处理器
public class IntentProcessor extends AbstractProcessor {

    //生成文件的对象
    private Filer mFiler;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
    }

    /**
     * 声明这个注解处理器要处理的注解
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(BindPath.class.getCanonicalName());      // 注解的类
    }

    /**
     * 声明我们的注解处理器所支持的java版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        ClassName activity = ClassName.get("android.app", "Activity");
//        TypeSpec.Builder mainActivityBuilder = TypeSpec.classBuilder("MainActivity")
//                .addModifiers(Modifier.PUBLIC)
//                .superclass(activity);
//        ClassName override = ClassName.get("java.lang", "Override");
//
//        ClassName bundle = ClassName.get("android.os", "Bundle");
//
//        ClassName nullable = ClassName.get("android.support.annotation", "Nullable");
//
//        ParameterSpec savedInstanceState = ParameterSpec.builder(bundle, "savedInstanceState")
//                .addAnnotation(nullable)
//                .build();
//
//        MethodSpec onCreate = MethodSpec.methodBuilder("onCreate")
//                .addAnnotation(override)
//                .addModifiers(Modifier.PROTECTED)
//                .addParameter(savedInstanceState)
//                .addStatement("super.onCreate(savedInstanceState)")
////                .addStatement("setContentView(R.layout.activity_main)")
//                .build();
//
//        TypeSpec mainActivity = mainActivityBuilder.addMethod(onCreate)
//                .build();
//
//        JavaFile file = JavaFile.builder("com.test", mainActivity).build();
//
//        try {
//            file.writeTo(mFiler);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        System.out.println("main: \n" + mainActivity);
        return false;
    }
}
