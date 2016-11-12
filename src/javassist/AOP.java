package javassist;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * javassist的aop直接操作的是字节码，与动态代理是不一样的。
 * @author may
 *
 */
public class AOP {
	
	public static void main(String[] args) throws Exception {
		AOP.test();
		
	}
	
	public static void test() throws Exception {
		
		ClassPool pool = ClassPool.getDefault();
		
		Loader loader = new Loader(pool);
		//获得User类
		CtClass cc = pool.get("javassist.User");
		//的到print方法
		CtMethod m = cc.getDeclaredMethod("print");
		//在print方法前插入这行代码
		m.insertBefore("System.out.println(\"insert into String before!\");");
		//在print方法后插入这行代码
		m.insertAfter("System.out.println(\"insert into String after!\");");
		
		//m.insertAt(lineNum, src)通过行号来插入代码
		//是这个类继承Person类
		cc.setSuperclass(pool.get("javassist.Person"));
		//写到这个目录，可以通过反编译软件来查看
		cc.writeFile("D:/javaCompiler/");
		
		Class<?> str = loader.loadClass("javassist.User");
		
		Constructor<?> constructor = str.getConstructor(String.class, int.class);
		
		Object obj = constructor.newInstance("hong", 22);
		
		Method method = str.getMethod("print");
		/**
		 * insert into String before!
		 * User [name=hong, age=22]
		 * insert into String after!
		 */
		method.invoke(obj);
		
		
	}

}
