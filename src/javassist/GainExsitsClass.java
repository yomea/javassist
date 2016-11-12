package javassist;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class GainExsitsClass {

	public static void test1() throws Exception {

		ClassPool pool = ClassPool.getDefault();
		//ClassPool pool = new ClassPool();
		// 增加类的classpath，在web服务器上需要指定这个,以便javassist能够找打classpath，这个参数只要是某个类的class就可以了，以防止找不到类的情况
		//ClassPath classPath = pool.insertClassPath(new ClassClassPath(String.class));
		//直接增加路径，如果你有个类在这个文件夹下，就可以到这个路径下加载
		//pool.insertClassPath("/usr/local/javalib");
		//通过URL的方式在这个下面http://www.javassist.org:80/java/找，后面是包名
		/*ClassPath cp = new URLClassPath("www.javassist.org", 80, "/java/", "org.javassist.");
		pool.insertClassPath(cp);
		//通过字节数组来设置路径
		ClassPool cp = ClassPool.getDefault();
		byte[] b = a byte array;
		String name = class name;
		cp.insertClassPath(new ByteArrayClassPath(name, b));
		CtClass cc = cp.get(name);
		
		
		//If you do not know the fully-qualified name of the class, then you can use makeClass() in ClassPool:

		ClassPool cp = ClassPool.getDefault();
		InputStream ins = an input stream for reading a class file;

		CtClass cc = cp.makeClass(ins);
		
		*/
		
		CtClass cc = pool.get("javassist.User");
		
		//cc.detach();将这个类从classpool中remove
		
		/**
		 * If a CtClass object is converted into a class file by writeFile(),
		 * toClass(), or toBytecode(), Javassist freezes that CtClass object.
		 * Further modifications of that CtClass object are not permitted. This
		 * is for warning the developers when they attempt to modify a class
		 * file that has been already loaded since the JVM does not allow
		 * reloading a class. 执行以下的toBytecode()方法将会被冻结，不能再修改，警告开发者这个类已被加载
		 * 出现java.lang.RuntimeException: javassist.User class is frozen
		 * 可通过defrost()来解冻
		 */
		// 得到User.class的字节码
		byte[] buf = cc.toBytecode();
		// 你可以直接将这个字节码文件写到一个class文件中
		System.out.println(Arrays.toString(buf));
		// 解冻
		cc.defrost();

		// 使用CtMethod的创建方法，使用CtNewMethod创建方法，使用new CtMethod创建方法
		// CtMethod m = CtNewMethod.make("public void print()
		// {System.out.println(name + \"-->\" + age);}", cc);
		CtMethod m = new CtMethod(CtClass.voidType, "printInfo", null, cc);

		// 将它的访问全向变为public
		m.setModifiers(Modifier.PUBLIC);

		// 设置方法体
		m.setBody("{System.out.println(name + \"-->\" + age);}");

		// 将这个方法加入到User中
		cc.addMethod(m);

		// 得到class对象
		Class<?> clazz = cc.toClass();
		// 获得有参构造器
		Constructor<?> con = clazz.getConstructor(String.class, int.class);
		// 创建对象
		Object obj = con.newInstance("youth", 22);
		// 得到添加的print方法
		Method method = clazz.getMethod("print");
		// 调用添加的print方法
		method.invoke(obj);

	}

	public static void main(String[] args) throws Exception {

		test1();
	}

}
