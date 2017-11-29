import com.data.sets.DataSetFirst;
import com.data.sets.DataSets;
import com.data.sets.GetRandomData;
import org.junit.Test;
import org.testng.Assert;

public class SecondUnitTest {

    @Test
    public void testMethod1(){
        DataSets data = new DataSetFirst();
        Assert.assertEquals(data.getUserName(), "Nicholas", "User name does not correct.");
    }

    @Test
    public void testMethod2(){
        DataSets data = new GetRandomData();
        Assert.assertTrue(data.getUserName().contains("UserName_"), "User name does not correct.");
    }
}
