package com.epam.clara.restwebapp.resources;

import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import com.epam.clara.restwebapp.dao.NodeDao;
import com.epam.clara.restwebapp.beans.Node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Controller
@Path("/nodes")
public class NodeResource {

    public static final String NODE_NOT_FOUND_MSG = "Node with ISBN[%s] does not exist";
    public static final String NODE_EXISTS_MSG = "Node with ISBN[%s] already exists";

    private NodeDao nodeDao;

    @Autowired
    public NodeResource(NodeDao nodeDao) {
        this.nodeDao = nodeDao;
    }

    @POST
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response create(Node node) {
        Node addedNode = nodeDao.create(node);
        return Response.ok(addedNode).build();
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response getAllByParent(@QueryParam("parentId") String parentId) {
        List<Node> nodes = new ArrayList<>(nodeDao.getAll(parentId));
        GenericEntity<List<Node>> nodesEntity = new GenericEntity<List<Node>>(nodes) {
        };
        return Response.ok(nodesEntity).build();
    }

    @PUT
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response update(Node node) throws NotFoundException {
        Node addedNode = nodeDao.update(node);
        return Response.ok(addedNode).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") String id) throws NotFoundException {
        nodeDao.delete(id);
        return Response.status(NO_CONTENT).build();
    }
}
