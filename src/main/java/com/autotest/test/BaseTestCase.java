package com.autotest.test;

import java.io.IOException;

/**
 * Created by Andrej Skeledzija 2017
 */
public abstract class BaseTestCase {

    public abstract void beforeMethod();

    public abstract void beforeTest();

    public abstract void afterMethod();

    public abstract void afterTest();

    public abstract void testLandingPage() throws IOException;
}
