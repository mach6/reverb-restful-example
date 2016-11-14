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
import org.mach6.reverb.models.UserStat;
import org.mach6.reverb.models.UserStats;

@Path("/userstats")
public class UserStatsResource {
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getUserStats() {
        List<UserStat> list = session.createQuery("FROM UserStat").list();
        UserStats stats = new UserStats();
        stats.setUserStat(list);

        return Response.ok(stats).build();
    }

    @Path("/{item_id}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getUserStat(@PathParam("item_id") int itemId) {
        // TODO Call reverb-core to get a user stat
        UserStat userStat = new UserStat();
        userStat.setItemId(itemId);

        return Response.ok(userStat).build();
    }
}
