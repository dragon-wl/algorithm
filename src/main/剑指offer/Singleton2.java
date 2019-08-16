/**
 * @author wanglong
 * @brief 静态内部类+懒汉模式：调用效率高，线程安全
 * @date 2019-08-15 22:41
 */
public class Singleton2 {

    /**
     * 静态内部类 + 懒汉模式
     */

    //私有构造函数
    private Singleton2(){

    }

    //静态内部类
    public static class SingletonIns{
        public final static Singleton2 singleton2= new Singleton2();
    }

    //返回静态内部类中的常量
    public Singleton2 getInstance(){
        return SingletonIns.singleton2;
    }
}


//参考博客：https://www.cnblogs.com/ngy0217/p/9006716.html