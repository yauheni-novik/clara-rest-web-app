package com.epam.clara.restwebapp.resources;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Yauheni_Novik on 3/7/2017.
 */
public class HierarchyRestApiService extends ResourceConfig {

    public HierarchyRestApiService() {
        register(HierarchyResource.class);
        register(NodeResource.class);
    }
}
