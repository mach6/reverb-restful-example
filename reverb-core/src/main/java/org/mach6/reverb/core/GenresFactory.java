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
