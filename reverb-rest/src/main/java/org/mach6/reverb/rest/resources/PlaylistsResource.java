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
import org.mach6.reverb.models.Playlist;
import org.mach6.reverb.models.Playlists;

@Path("/playlists")
public class PlaylistsResource {
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getPlaylists() {
        List<Playlist> list = session.createQuery("FROM Playlist").list();
        Playlists playlists = new Playlists();
        playlists.setPlaylist(list);

        return Response.ok(playlists).build();
    }

    @Path("/{playlist_id}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getPlaylist(@PathParam("playlist_id") int playlistId) {
        Playlist playlist = (Playlist) session.byId(Playlist.class).load(playlistId);
        return Response.ok(playlist).build();
    }
}
