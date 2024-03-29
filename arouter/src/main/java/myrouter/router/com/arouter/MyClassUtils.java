package myrouter.router.com.arouter;

import android.content.Context;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;

/**
 * @author liyongjian
 * @date 2019-08-07.
 * Description：
 */
public class MyClassUtils {
    //通过BaseDexClassLoader反射获取app所有的DexFile
    private static List<DexFile> getDexFiles(Context context) throws IOException {
        List<DexFile> dexFiles = new ArrayList<>();
        BaseDexClassLoader loader = (BaseDexClassLoader) context.getClassLoader();
        try {
            Field pathListField = field("dalvik.system.BaseDexClassLoader","pathList");
            Object list = pathListField.get(loader);
            Field dexElementsField = field("dalvik.system.DexPathList","dexElements");
            Object[] dexElements = (Object[]) dexElementsField.get(list);
            Field dexFilefield = field("dalvik.system.DexPathList$Element","dexFile");
            for(Object dex:dexElements){
                DexFile dexFile = (DexFile) dexFilefield.get(dex);
                dexFiles.add(dexFile);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return dexFiles;
    }

    private static Field field(String clazz,String fieldName) throws ClassNotFoundException, NoSuchFieldException {
        Class cls = Class.forName(clazz);
        Field field = cls.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }
    /**
     * 通过指定包名，扫描包下面包含的所有的ClassName
     *
     * @param context     U know
     * @param packageName 包名
     * @return 所有class的集合
     */
    public static Set<String> getFileNameByPackageName(Context context, final String packageName) throws IOException {
        final Set<String> classNames = new HashSet<>();

        List<DexFile> dexFiles = getDexFiles(context);
        for (final DexFile dexfile : dexFiles) {
            Enumeration<String> dexEntries = dexfile.entries();
            while (dexEntries.hasMoreElements()) {
                String className = dexEntries.nextElement();
                if (className.startsWith(packageName)) {
                    classNames.add(className);
                }
            }
        }
        return classNames;
    }
}
