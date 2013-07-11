package org.mach6.reverb.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mach6.reverb.core.GenreFactory;
import org.mach6.reverb.core.GenresFactory;
import org.mach6.reverb.models.Genre;
import org.mach6.reverb.models.Genres;

@Path("/genres")
public class GenresResource {
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getGenres() {
        //call the class in "core" .. no hibernate calls here
        Genres genres = GenresFactory.getAllGenres();
        return Response.ok(genres).build();
    }

    @Path("/{genre_id}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getGenre(@PathParam("genre_id") int genre_id) {
        //call the class in "core" .. no hibernate calls here
        Genre genre = GenreFactory.getById(1);
        return Response.ok(genre).build();
    }
}
