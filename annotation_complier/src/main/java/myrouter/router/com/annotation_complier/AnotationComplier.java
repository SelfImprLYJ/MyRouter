package myrouter.router.com.annotation_complier;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
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
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;

import myrouter.router.com.anotations.BindPath;

@AutoService(Processor.class) //注册注解处理器
public class AnotationComplier extends AbstractProcessor {

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
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    /**
     * 声明我们的注解处理器所支持的java版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
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
                TypeElement typeElement = (TypeElement) element;
                //获取key
                String key = typeElement.getAnnotation(BindPath.class).value();
                //获取到Activity的类对象的名字
                String activityName = typeElement.getQualifiedName().toString();
                map.put(key, activityName);
            }
            Writer writer = null;
//            //创建一个新类名
            String utilName = "ActivityUtil" + System.currentTimeMillis();
//            //创建文件
//            try {
//                JavaFileObject sourceFile = mFiler.createSourceFile("com.myrouter.util." + utilName);
//                writer = sourceFile.openWriter();
//                writer.write("package com.myrouter.util;\n");
//                writer.write("import android.app.Activity;\n");
//                writer.write("import myrouter.router.com.arouter.IRouter;\n" +
//                        "import myrouter.router.com.arouter.ARouter;\n" +
//                        "\n" +
//                        "public class " + utilName + " implements IRouter {\n" +
//                        "\n" +
//                        "    @Override\n" +
//                        "    public void putActivity(Activity activity) {");
//                Iterator<String> iterator = map.keySet().iterator();
//                while (iterator.hasNext()) {
//                    String key = iterator.next();
//                    String activityName = map.get(key);
//                    writer.write("ARouter.getInstance().putActivity(\"" + key + "\"," + activityName + ".class);");
//                }
//                writer.write("   }\n" +
//                        "}");
//            } catch (Exception e) {
//                System.out.println("Exception:" + e.getMessage());
//                e.printStackTrace();
//            }
//            finally {
//                if (writer != null) {
//                    try {
//                        writer.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
        }

        return false;
    }
}
