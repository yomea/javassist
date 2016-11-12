package javassist.classLoader.method;

/**
 * 这是window的修改版window2,此时的window2中的方法中增加了Point的引用
 * L加载了window2后发现还有Box和Point的引用，这里的point由L加载
 * L委托PL加载了Box，PL加载Box发现有Point的引用
 * 于是顺便加载了Point，此时出现了这么个情况，Box中的Point是PL加载的
 * Window2中的Point是由L加载的，调用widthIs()方法时，box.getSize();哪的是Box中的Point，
 * 即PL加载的point，赋给window2中由L加载的Point，出现错误，ClassCastException
 * @author may
 *
 */
class Point2 {
    private int x, y;
    public int getX() { return x; }
}

class Box2 {      // the initiator is L but the real loader is PL
    private Point2 upperLeft, size;
    public Point2 getSize() { return size; }
}

public class Window2 {    // loaded by a class loader L
    private Box2 box;
    public boolean widthIs(int w) {
        Point2 p = box.getSize();//
        return w == p.getX();
    }
}