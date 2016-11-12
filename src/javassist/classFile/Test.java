package javassist.classFile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.management.Attribute;

import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ClassFile;
import javassist.bytecode.FieldInfo;

public class Test {

	public static void main(String[] args) throws Exception {

		BufferedInputStream fin = new BufferedInputStream(Test.class.getResourceAsStream("Hello.class"));
		ClassFile cf = new ClassFile(new DataInputStream(fin));
		//cf.write(DataOutputStream);
		List<?> list = cf.getFields();
		
		Iterator<?> it = list.iterator();
		
		while(it.hasNext()) {
			it.next();
			it.remove();//删掉这个类所有的成员
			
			
		}
		
		cf.write(new DataOutputStream(new FileOutputStream(new File("D:/javaCompiler/haofan.class"))));
		
	}

}
