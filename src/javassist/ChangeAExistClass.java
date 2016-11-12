package javassist;

import java.io.IOException;

public class ChangeAExistClass {
	
	public static void main(String[] args) throws NotFoundException, IOException, CannotCompileException {
		//map
		ClassPool pool = ClassPool.getDefault();
		
		//ClassPool pool = new ClassPool(boolean useDefaultPath);
		//pool中还没有user的ctclass对象，初次使用会加载并且加入到classpool中
		CtClass cc = pool.get("javassist.User");
		//直接拿
		CtClass cc2 = pool.get("javassist.User");
		//更改了名字，相当直接改了key，但内容没变
		cc.setName("javassist.Pair");//改变类名
		//还是直接拿
		CtClass cc3 = pool.get("javassist.Pair");
		//由于这个key已经被更改了，所以当前是不存在的，会重新new一个ctClass出来
		CtClass cc4 = pool.get("javassist.User");
		
		cc4.writeFile();
		//执行writeFile后这个对象会被frozen
		cc4.setName("heeh");//java.lang.RuntimeException: javassist.User class is frozen
		CtClass cc5 = pool.get("javassist.User");
		cc5.setName("heeh");//java.lang.RuntimeException: javassist.User class is frozen
		//这种取值方法会创建一个新的，原先的还在
		CtClass cc6 = pool.getAndRename("javassist.User", "javassist.heeh");
		
		CtClass cc7 = pool.get("javassist.User");
		
		System.out.println(cc5 == cc7);//true
		
		System.out.println(cc5 == cc6);//false
		
		System.out.println(cc4 == cc5);//true
		
		System.out.println(cc == cc2);//true
		
		System.out.println(cc == cc3);//true
		
		System.out.println(cc == cc4);//false
	}

}
