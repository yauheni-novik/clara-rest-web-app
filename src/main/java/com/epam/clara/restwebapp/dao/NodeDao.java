package com.epam.clara.restwebapp.dao;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.epam.clara.restwebapp.util.Constants.*;

import com.epam.clara.restwebapp.beans.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class NodeDao {

    private static final Logger logger = LoggerFactory.getLogger(NodeDao.class);

    private MongoOperations mongoOperations;

    public NodeDao() {
    }

    public Node create(final Node nodeToCreate) {
        checkNotNull(nodeToCreate, "Argument[nodeToCreate] must not be null");

        mongoOperations.save(nodeToCreate);
        logger.info("Created [{}] node", nodeToCreate.getTitle());
        return nodeToCreate;
    }

    public List<Node> getAll(final String parentId) {
        final List<Node> nodes = mongoOperations.find(Query.query(Criteria.where(PARENT_FIELD).is(parentId)), Node.class);

        nodes.stream().forEach(node -> node.setChildren(getAll(node.get_id())));

        logger.info("Retrieved [{}] nodes", nodes.size());
        return nodes;
    }

    public Node update(final Node nodeToUpdate) {
        checkNotNull(nodeToUpdate, "Argument[nodeToUpdate] must not be null");

        mongoOperations.save(nodeToUpdate);
        logger.info("Updated [{}] node", nodeToUpdate.getTitle());
        return nodeToUpdate;
    }

    public void delete(final String nodeIdToDelete) {
        checkNotNull(nodeIdToDelete, "Argument[nodeIdToDelete] must not be null");

        mongoOperations.remove(Query.query(Criteria.where("_id").is(nodeIdToDelete)), Node.class);
    }

    public void setMongoOperations(final MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
}
