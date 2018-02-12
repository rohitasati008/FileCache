package com.sapient.FileCache;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
  urous Test :-)
     */
    public void testApp()
    {
    	 AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(App.class);
    	 SimpleCache s = (SimpleCache) a.getBean("simpleCache");
  //  System.out.println(s.getMaxAge());
        assertEquals(12,s.getMaxAge());
    }
}
