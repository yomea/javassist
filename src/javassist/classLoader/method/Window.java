package javassist.classLoader.method;

/**
 * window由L来加载，发现window中存在box，JVM要求也要加载box，于是L委托PL去加载这个
 * Box类，Box中有个point引用，于是PL又加载了Point
 * @author may
 *
 */

public class Window {    // loaded by a class loader L通过L加载器加载
    private Box box;
    public int getBaseX() { return box.getBaseX(); }
}

class Box {      // the initiator is L but the real loader is PL发起人是L加载器，但实际的加载器为PL
    private Point upperLeft, size;
    public int getBaseX() { return upperLeft.getX(); }
}

class Point {    // loaded by PL通过PL加载器加载
    private int x, y;
    public int getX() { return x; }
}



