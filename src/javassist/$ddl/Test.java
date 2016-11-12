package javassist.$ddl;

import java.lang.reflect.Method;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

/**
 * $0, $1, $2, ... this and actual parameters 
 * 
 * $args An array of parameters. The
 * type of $args is Object[]. 
 * 
 * $$ All actual parameters. For example, m($$) is
 * equivalent to m($1,$2,...), 
 * 
 * $cflow(...) cflow variable means "control flow"
 * 
 * $r The result type. It is used in a cast
 * expression. 
 * 
 * $w The wrapper type. It is used in a cast expression.-->Integer i = ($w)5; 
 * 
 * $_ The
 * resulting value 
 * 
 * $sig An array of java.lang.Class objects representing the
 * formal parameter types. 
 * 
 * $type A java.lang.Class object representing the
 * formal result type. 
 * 
 * $class A java.lang.Class object representing the class
 * currently edited.
 * 
 * @author may
 *
 */
public class Test {

	public static void main(String[] args) throws Exception {

		ClassPool pool = ClassPool.getDefault();

		CtClass cc = pool.makeClass("javassist.Girl");
		//引入Java.util
		pool.importPackage("java.util");
		
		CtMethod method = new CtMethod(CtClass.intType, "print",
				new CtClass[] { CtClass.intType, pool.get("java.lang.String") }, cc);

		method.setModifiers(Modifier.PUBLIC);

		method.setBody("{System.out.println($1 + \"-->\" + $2);System.out.println(Arrays.toString($args));return 100;}");

		method.instrument(
			    new ExprEditor() {
			        public void edit(MethodCall m)
			                      throws CannotCompileException
			        {
			            if (m.getClassName().equals("javassist.Girl")
			                          && m.getMethodName().equals("print"))
			                m.replace("{ { $1 = 0; $_ = $proceed($$); } }");
			        }
			    });
		
		cc.addMethod(method);
		
		CtMethod mm = CtMethod.make("public void test(){print(22, \"youth\");}", cc); 
		
		cc.addMethod(mm);
		
		Class<?> clazz = cc.toClass();

		Object obj = clazz.newInstance();

		Method m = clazz.getMethod("test");

		m.invoke(obj);

	}

}
