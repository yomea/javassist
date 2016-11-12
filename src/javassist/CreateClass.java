package javassist;

import java.lang.reflect.Constructor;

public class CreateClass {
	
	public static void main(String[] args) throws Exception {
		//创建一个类池
		ClassPool pool = ClassPool.getDefault();
		
		//创建一个从类池中查找class的classLoader
		Loader loader = new Loader(pool);
		
		//创建一个class
		CtClass ctClass = pool.makeClass("com.javassist.Hello");
		//创建成员变量name
		CtField name = CtField.make("private String name;", ctClass);
		//创建成员变量age
		CtField age = CtField.make("private int age;", ctClass);
		//创建password
		CtField password = new CtField(pool.get("java.lang.String"), "password", ctClass);
		//设置password的访问权限为private
		password.setModifiers(Modifier.PRIVATE);
		
		ctClass.addField(name);
		
		ctClass.addField(age);
		
		ctClass.addField(password);
		//创建方法getName
		CtMethod getName = CtMethod.make("public String getName() {return this.name;}", ctClass);
		//创建方法getAge
		CtMethod getAge = CtMethod.make("public int getAge() {return this.age;}", ctClass);
		
		ctClass.addMethod(getName);
		
		ctClass.addMethod(getAge);
		//创建构造器
		CtConstructor ctConstructor = new CtConstructor(new CtClass[]{CtClass.intType, pool.get("java.lang.String")}, ctClass);

		//$1表示第一个参数，$2表示第二个参数，$0表示this，还有其他的查看API吧
		ctConstructor.setBody("{age = $1; name = $2; int a = 2;System.out.println(age + \"-->\" + name);}");
		
		ctClass.addConstructor(ctConstructor);
		//将class文件写出
		ctClass.writeFile("D:/javaCompiler/");
		
		//Class<?> clazz = ctClass.toClass();
		//从类池中加载Hello类
		Class<?> clazz = loader.loadClass("com.javassist.Hello");
		
		Constructor<?> con = clazz.getConstructor(int.class, String.class);
		
		con.newInstance(22, "youth");
		
		System.out.println("完成");
		
	}

}
