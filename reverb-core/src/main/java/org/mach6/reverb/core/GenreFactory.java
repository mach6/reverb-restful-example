/*
 * Copyright (C) 2014 Doug Simmons
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance 
 * with the License.
 * 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0  
 */

package org.mach6.reverb.core;

import org.hibernate.Session;
import org.mach6.reverb.models.Genre;

public class GenreFactory {
    private static final Session session = HibernateUtil.openSession();

    public static Genre getById(int id) {
        session.beginTransaction();

        Genre genre = (Genre) session.byId(Genre.class).load(id);

        session.getTransaction().commit();
        return genre;
    }
}
