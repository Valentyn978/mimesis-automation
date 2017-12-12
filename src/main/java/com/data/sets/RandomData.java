package com.data.sets;

import com.helpers.DataProcessing;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.concurrent.atomic.AtomicInteger;

public class RandomData implements DataSets {

    private static AtomicInteger RAND_COUNT = new AtomicInteger(0);

    @Override
    public String getUserName() {
        return "UserName_" + RAND_COUNT.incrementAndGet() + "_" + DataProcessing.getRandomString(7);
    }

    @Override
    public String getUserLogin() {
        throw new  NotImplementedException();
    }

    @Override
    public String getUserPassword() {
        throw new  NotImplementedException();
    }

    @Override
    public String getPostDataUser() {
        throw new  NotImplementedException();
    }

    @Override
    public String getPutDataUser() {
        throw new  NotImplementedException();
    }

    @Override
    public String getSourceDataTranslator() {
        throw new  NotImplementedException();
    }

    @Override
    public String getResultDataTranslator() {
        throw new  NotImplementedException();
    }
}
