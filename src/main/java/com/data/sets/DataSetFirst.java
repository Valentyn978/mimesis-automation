package com.data.sets;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.helpers.DataProcessing.getRandomString;

public class DataSetFirst implements DataSets
{
    @Override
    public String getUserName() {
        return "Nicholas";
    }

    @Override
    public String getUserLogin() {
        throw new NotImplementedException();
    }

    @Override
    public String getUserPassword() {
        throw new NotImplementedException();
    }

    @Override
    public String getPostDataUser() {
        return "{\n" +
                "    \"postId\": 1,\n" +
                "    \"name\": \"id automation tests\",\n" +
                "    \"email\": \"automation@mail.com\",\n" +
                "    \"body\": \"New comment: " + getRandomString(5) + "\n" +
                "  }";
    }

    @Override
    public String getPutDataUser() {
        return "{\n" +
                "    \"postId\": 1,\n" +
                "    \"id\": 5,\n" +
                "    \"name\": \"vero eaque aliquid doloribus et culpa\",\n" +
                "    \"email\": \"Hayden@althea.biz\",\n" +
                "    \"body\": \"harum non quasi et ratione\\ntempore iure ex voluptates in ratione" +
                "   \\nharum architecto fugit inventore cupiditate\\nvoluptates magni quo et\"\n"
                + getRandomString(5);
    }
}
