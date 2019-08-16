/**
 * @author wanglong
 * @brief 单例模式：双重校验模式
 * @date 2019-08-15 22:25
 */
public class Singleton1 {

    /**
     * 双重校验+懒汉模式
     */

    //使用volatile保证可见性
    public volatile static Singleton1 singleton1;

    //构造函数设置为private，即不可被外部调用
    private Singleton1(){

    }

    //单例模式中的双重验证
    //   第一次验证滤掉实例已经存在的情况
    //   第二次验证保证等待和获取锁过程中实例仍未创建

    //懒汉模式
    //   仅当在调用时才new对象
    public static Singleton1 getInstance1(String[] args) {
        if(singleton1 == null){
            synchronized (Singleton1.class){
                if(singleton1 ==null)
                    singleton1 = new Singleton1();  //构造函数只能被类中函数调用
            }
        }
        return singleton1;
    }
}


//参考博客：https://www.cnblogs.com/ngy0217/p/9006716.html