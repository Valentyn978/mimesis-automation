package com.configuration;

import org.testng.ITestContext;
import org.testng.TestNGException;

import com.data.sets.DataSetFirst;
import com.data.sets.DataSetSecond;
import com.data.sets.DataSets;
import com.google.inject.AbstractModule;

public class AutomationMainModule extends AbstractModule {

    private ITestContext testContext;

    public AutomationMainModule(ITestContext testContext) {
        this.testContext = testContext;
    }

    @Override
    protected void configure() {
        String dataSet = null;
        try {
            dataSet = testContext.getCurrentXmlTest().getParameter("dataSet");
        } catch (TestNGException ignore){}
        if (null != dataSet) {
            switch (dataSet){
                case "firstDataSet":
                    bind(DataSets.class).toInstance(new DataSetFirst());
                    break;
                case "secondDataSet":
                    bind(DataSets.class).toInstance(new DataSetSecond());
            }
        } else {
            bind(DataSets.class).toInstance(new DataSetFirst());
        }
    }
}