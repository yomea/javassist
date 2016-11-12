package javassist;

/**
 * classpool的级联操作
 * @author may
 *
 */
public class CascadeClasspool {
	
	public static void main(String[] args) throws NotFoundException {
		ClassPool parent = ClassPool.getDefault();//拥有默认的类加载路径
		
		ClassPool child = new ClassPool(parent);
		
		parent.appendSystemPath();         // the same class path as the default one.
		
		child.insertClassPath("./classes");//设置child的class的路径，
		
		child.childFirstLookup = true;    // changes the behavior of the child.默认是false，默认classpool在
		//在调用get时是先从parent找的，如果为true就会先从子容器中找
		
		
	}

}
