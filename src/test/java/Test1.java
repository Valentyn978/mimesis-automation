import org.junit.Assert;
import org.junit.Test;

public class Test1 {

    @Test
    public void testMethod1(){
        System.out.printf("Test Method1; Class1");
        Assert.assertTrue(1 == 1);
    }

    @Test
    public void testMethod2(){
        System.out.printf("Test Method2; Class1");
        Assert.assertTrue(1 == 1);
    }
}
