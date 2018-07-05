import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.collections.Objects;

public class testParam {
    @Test
    @Parameters({"name","age"})
    public void test111(String name,int age){
        System.out.println("name="+name+";age="+age);
    }

    @Test
    public void test1(String name ,int age){
        System.out.println("name="+name+";age="+age);
    }
    @Test
    public void test2(String name ,int age){
        System.out.println("name="+name+";age="+age);
    }

}
