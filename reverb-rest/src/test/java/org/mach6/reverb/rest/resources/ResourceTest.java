/*
 * Copyright (C) 2014 Doug Simmons
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance 
 * with the License.
 * 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0  
 */

package org.mach6.reverb.rest.resources;

import static org.testng.Assert.*;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.core.util.FeaturesAndProperties;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.client.ClientFactory;

import org.mach6.reverb.rest.ServiceConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class ResourceTest {
    private static Logger logger = LoggerFactory.getLogger(ResourceTest.class);

    private static volatile JerseyTest jerseyTestSingleton;

    // test server config
    private static AppDescriptor webAppDesc = new WebAppDescriptor.Builder(ServiceConfig.RESOURCES_PACKAGE.toString())
            .initParam(JSONConfiguration.FEATURE_POJO_MAPPING, "true")
            .initParam(FeaturesAndProperties.FEATURE_FORMATTED, "true")
            .servletPath(ServiceConfig.SERVLET_URI.toString()).build();

    private static synchronized JerseyTest getSingleton() {
        if (jerseyTestSingleton != null) {
            return jerseyTestSingleton;
        }

        logger.info("Creating JerseyTest Singleton...");

        try {
            jerseyTestSingleton = new JerseyTest(webAppDesc) {
                @Override
                public synchronized void setUp() throws Exception {
                    logger.debug("In setUp(), jerseyTestSingleton");
                    // setup the server and test client
                    super.setUp();
                }

                @Override
                public synchronized void tearDown() throws Exception {
                    logger.debug("In tearDown(), jerseyTestSingleton");
                    // stop the test client and the server
                    super.tearDown();
                }

                @Override
                public int getPort(int defaultPort) {
                    logger.debug("In getPort(), jerseyTestSingleton");
                    String port = ServiceConfig.HTTP_PORT.toString();
                    if (null != port) {
                        try {
                            return Integer.parseInt(port);
                        } catch (NumberFormatException e) {
                            // ignore, we have a default port to fall back on
                        }
                    }
                    return defaultPort;
                }

                @Override
                public URI getBaseURI() {
                    logger.debug("In getBaseURI(), jerseyTestSingleton");
                    return UriBuilder.fromUri(ServiceConfig.HTTP_HOST.toString()).port(getPort(8080)).build();
                }

                @Override
                public synchronized WebResource resource() {
                    logger.debug("In resource(), jerseyTestSingleton");
                    return super.resource();
                }

                @Override
                // test client config
                public ClientFactory getClientFactory() {
                    logger.info("Creating Client Factory...");
                    return new ClientFactory() {

                        public Client create(ClientConfig clientConfig) {
                            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
                            clientConfig.getFeatures().put(FeaturesAndProperties.FEATURE_DISABLE_XML_SECURITY, true);
                            Client client = Client.create(clientConfig);
                            client.addFilter(new LoggingFilter(System.out));
                            return client;
                        }
                    };
                }
            };
        } catch (Exception e) {
            throw new RuntimeException("Unable to start Grizzy server.", e);
        }

        return jerseyTestSingleton;
    }

    @BeforeSuite(alwaysRun = true)
    public synchronized void beforeSuite(ITestContext ctx) throws Exception {
        List<String> groups = ctx.getCurrentXmlTest().getIncludedGroups();
        if (groups.isEmpty()) {
            startUp();
        }

        for (String group : groups) {
            if (group.equalsIgnoreCase("functional")) {
                startUp();
            }
        }
    }

    private synchronized void startUp() throws Exception {
        jerseyTestSingleton = getSingleton();
        jerseyTestSingleton.setUp();
        logger.info("Sleeping for 5 sec ...");
        Thread.sleep(5000);
        new ResourceTest().testApplicationWadl();
        logger.info("Sleeping for 2 sec ...");
        Thread.sleep(2000);
    }

    @AfterSuite(alwaysRun = true)
    public synchronized void afterSuite() throws Exception {
        if (jerseyTestSingleton != null) {
            jerseyTestSingleton.tearDown();
        }
    }

    public static synchronized WebResource resource() {
        logger.debug("In resource(), ResourceTest");
        return jerseyTestSingleton.resource();
    }

    /**
     * Test if a WADL document is available at the relative path "application.wadl".
     */
    public void testApplicationWadl() {
        WebResource webResource = resource();
        String serviceWadl = webResource.path("application.wadl").accept(MediaTypes.WADL).get(String.class);
        assertTrue(serviceWadl.length() > 0);
    }
}
