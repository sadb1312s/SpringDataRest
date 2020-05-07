package com.company.service.updatetable;

import org.apache.commons.lang3.ClassUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Updater {
    public static void update(Updatable entity, Map<String,String> params, Class entityClass) throws UpdateException{
        List<String> status = new ArrayList<>();

        params.forEach((k, v) -> {
            try {
                Field f = entityClass.getDeclaredField(k);

                f.setAccessible(true);
                String className = f.getType().getCanonicalName();

                Class c = ClassUtils.getClass(className);

                if (c.isPrimitive()){
                    c = ClassUtils.primitiveToWrapper(c);
                }

                Constructor<?> ctor = c.getConstructor(String.class);
                f.set(entity,ctor.newInstance(v));

            } catch (NoSuchFieldException | ClassNotFoundException e) {
                status.add(e.toString());
                e.printStackTrace();
            } catch (InstantiationException e) {
                status.add(e.toString());
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                status.add(e.toString());
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                status.add(e.toString());
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                status.add(e.toString());
                e.printStackTrace();
            }
        });

        String result = String.join(", ", status);
        if(!"".equals(result)) {
            throw  new UpdateException(result);
        }
    }
}
