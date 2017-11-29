package test.mixed;

import com.helpers.SetRequestHeaders;
import org.junit.Assert;

public class JUnit4FirstUnitTest {

    private SetRequestHeaders setHeaders = new SetRequestHeaders();

    @org.junit.Test
    public void testMethod1(){
        Object ob = setHeaders.setHeaders("http://test.com", "POST");

        Assert.assertEquals("Returned object from Set Headers does not valid", ob.getClass().getName(),
                "org.apache.http.client.methods.HttpPost");
    }

    @org.junit.Test
    public void testMethod2(){
        Object ob = setHeaders.setHeaders("http://test.com", "PATCH");

        Assert.assertEquals("Returned object from Set Headers does not valid", ob.getClass().getName(),
                "org.apache.http.client.methods.HttpPatch");
    }
}
