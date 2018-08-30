import com.helpers.RequestHeaders;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class HttpSetHeadersTest {

    private static Collection<String> expected = new ArrayList<>();
    private static Collection<String> given = new ArrayList<>();
    private RequestHeaders setHeaders = new RequestHeaders();

    @BeforeClass
    public static void expectedDataPreparation() {
        expected.add("Content-Type");
        expected.add("application/json");
        expected.add("Accept");
        expected.add("application/json");
        expected.add("Content-Type");
        expected.add("text/html");
    }

    @Test
    public void testMethodPost() {
        HttpPost ob = setHeaders.setHeaders("http://test.com", "POST");

        Assert.assertEquals(ob.getMethod(), "POST");
        HeaderIterator headerIterator = ob.headerIterator();
        while (headerIterator.hasNext()) {
            Header header = headerIterator.nextHeader();
            given.add(header.getName());
            given.add(header.getValue());
        }

        Assert.assertTrue(expected.containsAll(given));

        Assert.assertEquals("Returned object from Set Headers does not valid", ob.getClass().getName(),
                "org.apache.http.client.methods.HttpPost");
    }

    @Test
    public void testMethodPatch() {
        HttpPatch ob = setHeaders.setHeaders("http://test.com", "PATCH");

        Assert.assertEquals(ob.getMethod(), "PATCH");
        HeaderIterator headerIterator = ob.headerIterator();
        while (headerIterator.hasNext()) {
            Header header = headerIterator.nextHeader();
            given.add(header.getName());
            given.add(header.getValue());
        }

        Assert.assertTrue(expected.containsAll(given));

        Assert.assertEquals("Returned object from Set Headers does not valid", ob.getClass().getName(),
                "org.apache.http.client.methods.HttpPatch");
    }
}
