package org.wechat.component;

import org.springframework.stereotype.Component;
import org.wechat.mine.task.AbstractTask;

import java.lang.reflect.InvocationTargetException;


@Component
public class FunctionCaller {
  public String[] getDataList(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Class<? extends AbstractTask> targetClass = (Class<? extends AbstractTask>) Class.forName(className);
    // 创建目标类的实例
    AbstractTask instance = targetClass.getDeclaredConstructor().newInstance();
    String[] dataList = instance.getDataList();
    return dataList;
  }



  public String[] getDataList(String className, String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//    System.out.println(className);
//    System.out.println(args);
    Class<? extends AbstractTask> targetClass = (Class<? extends AbstractTask>) Class.forName(className);
    // 创建目标类的实例
    AbstractTask instance = targetClass.getDeclaredConstructor().newInstance();
    String[] dataList = instance.getDataList(args);
    return dataList;
  }
}
