/*
 * Copyright (C) 2014 Doug Simmons
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance 
 * with the License.
 * 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0  
 */

package org.mach6.reverb.rest.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mach6.reverb.models.Collection;
import org.mach6.reverb.models.Collections;

@Path("/collections")
public class CollectionsResource {
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getCollections() {
        // TODO Call reverb-core to get all collections
        Collections collections = new Collections();
        collections.setCollection(new ArrayList<Collection>());

        return Response.ok(collections).build();
    }

    @Path("/{collection_id}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getCollection(@PathParam("collection_id") int collectionId) {
        // TODO Call reverb-core to get a collection
        Collection collection = new Collection();
        collection.setCollectionId(collectionId);

        return Response.ok(collection).build();
    }
}
