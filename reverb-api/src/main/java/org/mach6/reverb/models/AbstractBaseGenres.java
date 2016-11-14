/*
 * Copyright (C) 2014 Doug Simmons
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance 
 * with the License.
 * 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0  
 */

package org.mach6.reverb.models;

import java.util.List;

/**
 * An abstract base class for the XJC generated class "Genres". This class adds additional capabilities to the generated
 * one.
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
