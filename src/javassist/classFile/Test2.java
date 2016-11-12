package javassist.classFile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;

import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Mnemonic;

/**
 * CodeIterator类所包含的方法
 * 
 * void begin() Move to the first instruction.
 * 
 * void move(int index) Move to the
 * instruction specified by the given index. 
 * 
 * boolean hasNext() Returns true if
 * there is more instructions. 
 * 
 * int next() Returns the index of the next
 * instruction. Note that it does not return the opcode of the next instruction.
 * 
 * int byteAt(int index) Returns the unsigned 8bit value at the index. 
 * 
 * int u16bitAt(int index) Returns the unsigned 16bit value at the index.
 * 
 * int write(byte[] code, int index) Writes a byte array at the index. 
 * 
 * void insert(int index, byte[] code) Inserts a byte array at the index. Branch
 * offsets etc. are automatically adjusted.
 * 
 * @author may
 *
 */
public class Test2 {

	public static void main(String[] args) throws Exception {

		BufferedInputStream fin = new BufferedInputStream(Test2.class.getResourceAsStream("Hello.class"));
		ClassFile cf = new ClassFile(new DataInputStream(fin));

		MethodInfo methodInfo = cf.getMethod("getB");// 假设这个方法未被重写
		// 获得代码属性
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();

		CodeIterator ci = codeAttribute.iterator();

		while (ci.hasNext()) {
			int index = ci.next();
			int op = ci.byteAt(index);
			/**
			 * aload_0 
			 * getfield 
			 * areturn
			 */
			System.out.println(Mnemonic.OPCODE[op]);// 显示getB内的所有的指令

		}

	}

}
