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
