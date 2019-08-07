package myrouter.router.com.annotation_complier;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;

import myrouter.router.com.anotations.BindPath;

@AutoService(Processor.class) //注册注解处理器
public class AnotationProcessor extends AbstractProcessor {

    //生成文件的对象
    private Filer mFiler;
    private String moduleName;

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
//        Set<String> types = new HashSet<>();
//        types.add(BindPath.class.getCanonicalName());
//        return types;
        return Collections.singleton(BindPath.class.getCanonicalName());      // 注解的类
    }

    /**
     * 声明我们的注解处理器所支持的java版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
//        return processingEnv.getSourceVersion();
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedOptions() {
        return new HashSet<String>() {{
            this.add(Constants.KEY_MODULE_NAME);
        }};
    }

    /**
     * 自动生成代码
     *
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindPath.class);
//        TypeElement 类节点
//        ExceptionTableElement 方法节点
//        VariableElement 成员变量的节点
        if (elements != null) {
            Map<String, String> map = new HashMap<>();
            for (Element element : elements) {
                addRoute(element);
                TypeElement typeElement = (TypeElement) element;
                //获取key
                String key = typeElement.getAnnotation(BindPath.class).value();
                //获取到Activity的类对象的名字
                String activityName = typeElement.getQualifiedName().toString();
                map.put(key, activityName);
            }

            ClassName aRouter = ClassName.get("myrouter.router.com.arouter","ARouter");
            ClassName iRouter = ClassName.get("myrouter.router.com.arouter","IRouter");

            TypeSpec.Builder activityUtilBuilder = TypeSpec.classBuilder("ARouter_" + moduleName)
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(iRouter);

            ClassName override = ClassName.get("java.lang", "Override");

            Iterator<String> iterator = map.keySet().iterator();


            MethodSpec.Builder builder = MethodSpec.methodBuilder("putActivity")
                    .addAnnotation(override)
                    .addModifiers(Modifier.PUBLIC);

            while (iterator.hasNext()) {
                String key = iterator.next();
                String activityName = map.get(key);
                builder.addStatement("$T.getInstance().putActivity($S,$N)", aRouter,key,activityName+".class");
            }

//                    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
//                    .addStatement("ARouter.getInstance().putActivity(\"\",MainActivity.class);")
//                    .addParameter(savedInstanceState)
//                    .addStatement("super.onCreate(savedInstanceState)")
//                .addStatement("setContentView(R.layout.activity_main)")
            MethodSpec putActivity =     builder.build();

            TypeSpec activityUtil = activityUtilBuilder.addMethod(putActivity)
                    .build();

            JavaFile file = JavaFile.builder("com.myrouter.util", activityUtil).build();

            try {
                file.writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private void addRoute(Element e) {
        BindPath annotation = e.getAnnotation(BindPath.class);
        String path = annotation.value();
        if (path != null && path.length() > 0){
            moduleName = path.replace("/","_");
//            moduleName = path.substring(0,path.lastIndexOf("/"));

            System.out.println("options0-------------->>>>  " + path);
        }

    }
}
