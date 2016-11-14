/*
 * Copyright (C) 2014 Doug Simmons
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance 
 * with the License.
 * 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0  
 */

package org.mach6.reverb.core;

import java.util.List;

import org.hibernate.Session;
import org.mach6.reverb.models.Genre;
import org.mach6.reverb.models.Genres;

public class GenresFactory {
    private static final Session session = HibernateUtil.openSession();

    @SuppressWarnings("unchecked")
    public static Genres getAllGenres() {
        List<Genre> list = session.createQuery("FROM Genre").list();
        Genres genres = new Genres();
        genres.setGenres(list);
        return genres;
    }
}
