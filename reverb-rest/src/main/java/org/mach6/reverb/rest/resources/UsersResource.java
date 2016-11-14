/*
 * Copyright (C) 2014 Doug Simmons
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance 
 * with the License.
 * 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0  
 */

package org.mach6.reverb.rest.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.mach6.reverb.core.HibernateUtil;
import org.mach6.reverb.models.User;
import org.mach6.reverb.models.Users;

@Path("/users")
public class UsersResource {
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getUsers() {
        List<User> list = session.createQuery("FROM User").list();
        Users users = new Users();
        users.setUser(list);

        return Response.ok(users).build();
    }

    @Path("/{user_id}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getUser(@PathParam("user_id") int userId) {
        // TODO Call reverb-core to get a user
        User user = new User();
        user.setUserId(userId);

        return Response.ok(user).build();
    }
}
