import com.helpers.SetRequestHeaders;
import org.junit.Assert;
import org.junit.Test;

public class Test1 {

    private SetRequestHeaders setHeaders = new SetRequestHeaders();

    @Test
    public void testMethod1(){
        System.out.printf("Test Method1; Class1");
        Object ob = setHeaders.setHeaders("http://test.com", "POST");

        Assert.assertEquals(ob.getClass().getName(), "org.apache.http.client.methods.HttpPost");
    }

    @Test
    public void testMethod2(){
        System.out.printf("Test Method2; Class1");
        Object ob = setHeaders.setHeaders("http://test.com", "PATCH");

        Assert.assertEquals(ob.getClass().getName(), "org.apache.http.client.methods.HttpPatch");
    }
}
