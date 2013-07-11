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
