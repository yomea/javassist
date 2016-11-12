package javassist;

import javassist.bean.Hello;

public class JavassistError {
	
	public static void main(String[] args) throws Exception {
		
		
		Hello hello = new Hello();
		
		ClassPool pool = ClassPool.getDefault();
		
		
		CtClass cc = pool.get("javassist.bean.Hello");
		//出错，告诉你出现了重复加载的类
		Class<?> clazz = cc.toClass();//attempted  duplicate class definition for name: "javassist/bean/Hello"
		
		ClassLoader classLoader = JavassistError.class.getClassLoader();
		
		Class<?> clazz2 = cc.toClass(classLoader.getParent());//使用它的父加载器就不会出错，因为使用的不是同一个类加载器,不过。。。。看下面
		/**
		 *  java.lang.ClassCastException: javassist.bean.Hello cannot be cast to javassist.bean.Hello
		 *  为什么会出这样的错误呢，是由于加载器不同导致的具体请看本项目的javassist.classLoader.method这个包下的说明
		 */
		Hello hello2 = (Hello) clazz2.newInstance();
		
		System.out.println(classLoader.getParent());
		
	}

}
