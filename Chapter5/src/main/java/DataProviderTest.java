import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {
    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name="+name+";age="+age);

    }
    @DataProvider(name = "data")
    public Object[][] providerData(){
        Object[][] o=new Object[][]{
                {"zhangsan",20},
                {"lisi",30},
                {"wangwu",40}
        };

        return o;
    }
    @Test(dataProvider = "methodData")
    public void test1(String name,int age){
        System.out.println("test111方法的name="+name+";age="+age);
    }
    @Test(dataProvider = "methodData")
    public void test2(String name,int age){
        System.out.println("test2222方法的name="+name+";age="+age);
    }
    @DataProvider(name = "methodData")
    public Object[][] provider(Method method){
        Object[][] objects=null;
        if(method.getName().equals("test1")){
            objects=new Object[][]{
                    {"大佬",20},
                    {"逗比",30}
            };
        }else if(method.getName().equals("test2")){
            objects=new Object[][]{
                    {"张三",50},
                    {"菜鸡",25}
            };
        }
        return objects;
    }
}
