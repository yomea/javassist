package javassist.annotation;

import javassist.ClassPool;
import javassist.CtClass;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
		ClassPool pool = ClassPool.getDefault();
		
		CtClass cc = pool.get("javassist.annotation.User");
		//获得类上面的所有的annotation
		Object[] annotations = cc.getAnnotations();
		
		for (Object object : annotations) {
			//找到Table annotation
			if(object instanceof Table) {
				
				System.out.println(((Table)object).value());
			}
		}
		
	}

}
