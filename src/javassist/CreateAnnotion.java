package javassist;

import java.io.IOException;

/**
 * package javassist;
 * 
 * import java.lang.annotation.Annotation;
 * 
 * public @interface Table { String value(); }
 * 
 * @author may
 *
 */
public class CreateAnnotion {

	public static void main(String[] args) throws CannotCompileException, IOException {

		ClassPool pool = ClassPool.getDefault();
		// 创建一个annotation
		CtClass cc = pool.makeAnnotation("javassist.Table");
		// 创建一个value参数
		CtMethod m = CtMethod.make("String value();", cc);

		cc.addMethod(m);

		cc.writeFile("d:/javaCompiler/");

	}

}
