[![Build Status](https://travis-ci.org/mach6/reverb-restful-example.svg?branch=master)](https://travis-ci.org/mach6/reverb-restful-example)

reverb-restful-example
======================

A fictional music RESTful service built with Java


It utilizes;
 * Jersey
 * Maven3
 * Hibernate for JDBC access and mapping to a H2 DB for testing
 * JAXB for model creation (Request/Response objects in the "api" jar)
 * Hamcrest BeanMatcher for unit testing the model classes
 * Grizzly and JerseyTest for local REST API functional testing. Thus, there is no need to the deploy a WAR to a Tomcat/Jboss/Other server
 * Jackson JSON for serialization of both XML and JSON
 * SLFHJ and LogBack for logging
