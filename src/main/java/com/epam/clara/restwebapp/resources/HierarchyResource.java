package com.epam.clara.restwebapp.resources;

import com.epam.clara.restwebapp.beans.Hierarchy;
import com.epam.clara.restwebapp.beans.Node;
import com.epam.clara.restwebapp.dao.HierarchyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.Response.Status.NO_CONTENT;

/**
 * Created by Yauheni_Novik on 3/7/2017.
 */
@Controller
@Path("/hierarchies")
public class HierarchyResource {
    private HierarchyDao hierarchyDao;

    @Autowired
    public HierarchyResource(HierarchyDao hierarchyDao) {
        this.hierarchyDao = hierarchyDao;
    }

    @POST
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response create(Hierarchy hierarchy) {
        Hierarchy addedHierarchy = hierarchyDao.create(hierarchy);
        return Response.ok(addedHierarchy).build();
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response getAllByParent(@QueryParam("parentId") String parentId) {
        List<Hierarchy> hierarchies = new ArrayList<>(hierarchyDao.getAll());
        GenericEntity<List<Hierarchy>> nodesEntity = new GenericEntity<List<Hierarchy>>(hierarchies) {
        };
        return Response.ok(nodesEntity).build();
    }

    @PUT
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response update(Hierarchy hierarchy) throws NotFoundException {
        Hierarchy addedHierarchy = hierarchyDao.update(hierarchy);
        return Response.ok(addedHierarchy).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") String id) throws NotFoundException {
        hierarchyDao.delete(id);
        return Response.status(NO_CONTENT).build();
    }
}
