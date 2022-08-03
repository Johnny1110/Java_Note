# 檢查 JavaBean 的 Field 是否恰當填值 （不為空字串或 null）

<br>


------

<br>

## 主程式

<br>

```java
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {

    // 檢查 JavaBean 的 Field 是否恰當填值 (不為空字串或 null)
    // 除了標註 @NonEssential 的 Field 不需要檢查
    public static boolean isBeanFilledProperly(Object object) throws InvocationTargetException, IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        List<String> skipMathodNames = new ArrayList<>();
        for(Field field : fields){
            if (field.getAnnotation(NonEssential.class) != null){
                if (field.getType().toString().equals("boolean") || field.getType().toString().equals("class java.lang.Boolean")){
                    skipMathodNames.add("is" + upperCaseFirst(field.getName()));
                }else{
                    skipMathodNames.add("get" + upperCaseFirst(field.getName()));
                }
            }
        }

        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method m : methods){
            if (m.getName().startsWith("get") || m.getName().startsWith("is")){
                if(skipMathodNames.contains(m.getName())){
                    continue;
                }
                Object value = m.invoke(object);
                if (value == null){
                    return false;
                }
                if (value instanceof String){
                    if (value.equals("")){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Country country = new Country();
        country.setCountrycode("TW");
        country.setCountryname("台灣");
        country.setId(1L);
        country.setA(true);
        country.setB(true);
        System.out.println(isBeanFilledProperly(country));
    }
}
```

<br>
<br>

## NonEssential 標記

<br>
<br>

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface NonEssential {
}
```

<br>
<br>

## 測試用 Java Bean

<br>

```java
import com.frizo.lab.mybatis.utils.NonEssential;

public class Country {
    private Long id;
    private String countryname;
    private Boolean a;
    @NonEssential
    private boolean b;
    @NonEssential
    private String countrycode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public Boolean getA() {
        return a;
    }

    public void setA(Boolean a) {
        this.a = a;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
}
```
