package com.epam.clara.restwebapp.resources;

import static java.lang.String.format;
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
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Response create(Node node) {
    nodeDao.create(node);
    return Response.status(NO_CONTENT).build();
  }

  @GET
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Response getAllByParent(@QueryParam("parentId") String parentId) {
    List<Node> nodes = new ArrayList<>(nodeDao.getAll(parentId));
    GenericEntity<List<Node>> nodesEntity = new GenericEntity<List<Node>>(nodes) {
    };
    return Response.ok(nodesEntity).build();
  }

  @PUT
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Response update(Node node) throws NotFoundException {
    nodeDao.update(node);
    return Response.status(NO_CONTENT).build();
  }

  @DELETE
  @Path("{id}")
  public Response delete(@PathParam("id") String id) throws NotFoundException {
    nodeDao.delete(id);
    return Response.status(NO_CONTENT).build();
  }
}
