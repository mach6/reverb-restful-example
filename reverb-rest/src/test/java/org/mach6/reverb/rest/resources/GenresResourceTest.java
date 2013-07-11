package org.mach6.reverb.rest.resources;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mach6.reverb.models.Genre;
import org.mach6.reverb.models.Genres;
import org.testng.annotations.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GenresResourceTest extends ResourceTest {

    @Test(groups = "unit")
    public void getGenre_unit() {
        GenresResource resource = new GenresResource();
        Response response = resource.getGenre(1);
        assertNotNull(response);
        assertEquals(response.getStatus(), 200, "did not get back HTTP 200 OK");
        
        Genre g = (Genre) response.getEntity();
        assertEquals(g.getGenreId(), 1);
        assertEquals(g.getName(), "Classic Rock");
    }

    @Test(groups = "unit")
    public void getGenres_unit() {
        GenresResource resource = new GenresResource();
        Response response = resource.getGenres();
        assertNotNull(response);
        assertEquals(response.getStatus(), 200, "did not get back HTTP 200 OK");
        
        Genres g = (Genres) response.getEntity();
        assertNotNull(g);
        assertEquals(g.getGenres().get(0).getGenreId(), -1);
    }
    
    @Test(groups = "functional")
    public void getGenre() {
        WebResource webResource = resource();
        ClientResponse response = webResource.path("genres/1").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        assertNotNull(response);
        assertEquals(response.getClientResponseStatus(), ClientResponse.Status.OK, "did not get back HTTP 200 OK");

        Genre g = response.getEntity(Genre.class);
        assertEquals(g.getGenreId(), 1);
        assertEquals(g.getName(), "Classic Rock");
    }

    @Test(groups = "functional")
    public void getGenres() {
        WebResource webResource = resource();
        ClientResponse response = webResource.path("genres").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        assertNotNull(response);
        assertEquals(response.getClientResponseStatus(), ClientResponse.Status.OK, "did not get back HTTP 200 OK");
        
        Genres g = response.getEntity(Genres.class);
        assertNotNull(g);
        assertEquals(g.getGenres().get(0).getGenreId(), -1);
    }
}
