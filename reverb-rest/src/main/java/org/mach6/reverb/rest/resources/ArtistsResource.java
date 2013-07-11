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
import org.mach6.reverb.models.Artist;
import org.mach6.reverb.models.Artists;

@Path("/artists")
public class ArtistsResource {
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getArtists() {
        List<Artist> list = session.createQuery("FROM Artist").list();
        Artists artists = new Artists();
        artists.setArtist(list);
        
        return Response.ok(artists).build();
    }

    @Path("/{artist_id}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getArtist(@PathParam("artist_id") int artistId) {
        Artist artist = (Artist) session.byId( Artist.class ).load(artistId);        
        return Response.ok(artist).build();
    }
}
