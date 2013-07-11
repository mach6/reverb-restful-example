package org.mach6.reverb.models;

import java.util.List;

/**
 * An abstract base class for the XJC generated class "Genres".
 * This class adds additional capabilities to the generated one.
 */
public abstract class AbstractBaseGenres {    
    public Genre getGenreByName(String name) {
        for (Genre g : getGenres()) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        return null;
    }

    public Genre getGenreById(int id) {
        for (Genre g : getGenres()) {
            if (g.getGenreId() == id) {
                return g;
            }
        }
        return null;
        
    }
    
    public abstract List<Genre> getGenres();
}
