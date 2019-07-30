import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BakerProxyHandler implements InvocationHandler {

    private Object target; // target 爲真正的 Baker

    public Object getProxyInstance(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        greeting();
        method.invoke(target, args); // target 的方法實際被觸發。
        account();
        return null;
    }

    private void greeting(){
        System.out.println("您好歡迎光臨...");
    }

    private void account(){
        System.out.println("結賬印發票...");
    }
}
