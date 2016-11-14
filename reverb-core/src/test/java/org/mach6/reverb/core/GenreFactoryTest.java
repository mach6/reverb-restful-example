/*
 * Copyright (C) 2014 Doug Simmons
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance 
 * with the License.
 * 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0  
 */

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
