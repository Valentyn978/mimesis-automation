import com.helpers.RequestHeaders;
import org.junit.Assert;
import org.junit.Test;

public class FirstUnitTest {

    private RequestHeaders setHeaders = new RequestHeaders();

    @Test
    public void testMethod1(){
        Object ob = setHeaders.setHeaders("http://test.com", "POST");

        Assert.assertEquals("Returned object from Set Headers does not valid", ob.getClass().getName(),
                "org.apache.http.client.methods.HttpPost");
    }

    @Test
    public void testMethod2(){
        Object ob = setHeaders.setHeaders("http://test.com", "PATCH");

        Assert.assertEquals("Returned object from Set Headers does not valid", ob.getClass().getName(),
                "org.apache.http.client.methods.HttpPatch");
    }
}
