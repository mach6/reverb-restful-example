/*
 * Copyright (C) 2014 Doug Simmons
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance 
 * with the License.
 * 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0  
 */

package org.mach6.reverb.models;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BeanLikeTest {
    private Class<?>[] beanClazzes;

    public BeanLikeTest() {
        // the list of bean classes to test
        this(Genre.class,
                Artist.class);
    }

    public BeanLikeTest(Class<?>... clazz) {
        this.beanClazzes = clazz;
    }

    /*
     * break down the list of classes into separate bean classes to feed the the @Test method below
     */
    @DataProvider(name = "clazzProvider")
    public Object[][] dataProvider() {
        Object[][] r = new Object[beanClazzes.length][1];
        for (int i = 0; i < beanClazzes.length; i++) {
            r[i][0] = beanClazzes[i];
        }

        return r;
    }

    /**
     * performs a series of tests for bean capabilities on each class provided by the @DataProvider
     */
    @Test(groups = "unit", dataProvider = "clazzProvider")
    public void testForBeanLike(Class<?> clazz) {
        // call the hamcrest BeanMatchers library..
        assertThat(clazz, allOf(hasValidBeanConstructor(),
                hasValidGettersAndSetters(),
                hasValidBeanHashCode(),
                hasValidBeanEquals(),
                hasValidBeanToString()));
    }
}
