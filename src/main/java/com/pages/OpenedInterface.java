package com.pages;

import com.configuration.driver.PageDriverImpl;
import org.testng.ITestContext;

public interface OpenedInterface {
    public PageDriverImpl open(ITestContext context);
    public boolean isOpen();
}
