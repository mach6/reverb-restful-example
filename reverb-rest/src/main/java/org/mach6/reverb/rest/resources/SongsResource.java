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
import org.mach6.reverb.models.Song;
import org.mach6.reverb.models.Songs;

@Path("/songs")
public class SongsResource {
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getSongs() {
        List<Song> list = session.createQuery("FROM Song").list();
        Songs songs = new Songs();
        songs.setSong(list);

        return Response.ok(songs).build();
    }

    @Path("/{song_id}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getSong(@PathParam("song_id") int songId) {
        Song song = (Song) session.byId(Song.class).load(songId);
        return Response.ok(song).build();
    }
}
