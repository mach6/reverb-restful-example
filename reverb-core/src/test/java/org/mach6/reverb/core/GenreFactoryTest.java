package org.mach6.reverb.core;

import static org.testng.Assert.*;

import org.mach6.reverb.core.GenreFactory;
import org.mach6.reverb.models.Genre;
import org.testng.annotations.Test;

public class GenreFactoryTest {

    @Test(groups = "unit")
    public void getById() {
        Genre genre = GenreFactory.getById(1);
        assertNotNull(genre);
        assertEquals(genre.getGenreId(), 1);
        assertEquals(genre.getName(), "Classic Rock");
    }
}
