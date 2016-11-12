package javassist;

public class CreateInterface {

	public static void main(String[] args) throws Exception {

		ClassPool pool = ClassPool.getDefault();
		//创建interface
		CtClass cc = pool.makeInterface("javassist.Test");
		//创建public static void final的成员变量
		CtField name = CtField.make("public static final String name = \"hello world\";", cc);

		cc.addField(name);
		//创建一个抽象方法
		CtMethod method = CtNewMethod.abstractMethod(pool.get("java.lang.String"), "getName",
				new CtClass[] { pool.get("java.lang.String") }, null, cc);
		
		
		cc.addMethod(method);
		
		cc.writeFile("D:/javaCompiler/");
	}

}
