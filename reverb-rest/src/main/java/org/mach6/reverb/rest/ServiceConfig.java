/*
 * Copyright (C) 2014 Doug Simmons
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance 
 * with the License.
 * 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0  
 */

package org.mach6.reverb.rest;

public enum ServiceConfig {
    SERVLET_URI("/"),
    HTTP_HOST("http://localhost"),
    HTTP_PORT("8080"),
    RESOURCES_PACKAGE("org.mach6.reverb");

    private String value;

    private ServiceConfig(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
