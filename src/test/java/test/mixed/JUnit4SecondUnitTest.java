package test.mixed;

import com.data.sets.DataSetFirst;
import com.data.sets.DataSets;
import com.data.sets.GetRandomData;
import org.junit.Assert;

public class JUnit4SecondUnitTest {

    @org.junit.Test
    public void testMethod1(){
        DataSets data = new DataSetFirst();
        Assert.assertEquals("User name does not correct.", data.getUserName(), "Nicholas");
    }

    @org.junit.Test
    public void testMethod2(){
        DataSets data = new GetRandomData();
        Assert.assertTrue("User name does not correct.", data.getUserName().contains("UserName_"));
    }
}
