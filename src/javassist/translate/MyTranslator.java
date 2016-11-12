package javassist.translate;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.Translator;

public class MyTranslator implements Translator {

	/**
	 * 在loader一个类时触发
	 */
	@Override
	public void onLoad(ClassPool pool, String classname) throws NotFoundException, CannotCompileException {
	
		System.out.println("MyTranslator onLoad method...");
	}

	/**
	 * 在loader绑定时就触发
	 */
	@Override
	public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
		System.out.println("MyTranslator start method...");
		
	}

}
