package javassist.translate;

import javassist.ClassPool;
import javassist.Loader;

public class Test {
	
	public static void main(String[] args) throws Throwable {
		
		ClassPool pool = ClassPool.getDefault();
		
		Loader loader = new Loader(pool);
		//添加监听器
		MyTranslator t = new MyTranslator();
		
		loader.addTranslator(pool, t);//当添加时就会启动这个translator的start方法
		//调用另一个含有main方法的类，第二个参数为Hehe类的main方法的参数
		//并且会启动translator的onLoad的方法，因为加载了Hehe类
		loader.run("javassist.translate.Hehe", new String[]{"a", "b"});
		
		//loader.delegateLoadingOf(classname);加载委托给其他的加载器
		
		
	}

}
