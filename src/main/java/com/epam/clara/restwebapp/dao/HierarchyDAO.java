package com.epam.clara.restwebapp.dao;

import com.epam.clara.restwebapp.beans.Hierarchy;
import com.epam.clara.restwebapp.beans.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static com.epam.clara.restwebapp.util.Constants.PARENT_FIELD;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Yauheni_Novik on 3/7/2017.
 */
public class HierarchyDao {
    private static final Logger logger = LoggerFactory.getLogger(NodeDao.class);

    private MongoOperations mongoOperations;

    public Hierarchy create(final Hierarchy hierarchyToCreate) {
        checkNotNull(hierarchyToCreate, "Argument[hierarchyToCreate] must not be null");

        mongoOperations.save(hierarchyToCreate);
        logger.info("Created [{}] hierarchy", hierarchyToCreate.getName());
        return hierarchyToCreate;
    }

    public List<Hierarchy> getAll() {
        final List<Hierarchy> hierarchies = mongoOperations.findAll(Hierarchy.class);

        logger.info("Retrieved [{}] hierarchies", hierarchies.size());
        return hierarchies;
    }

    public Hierarchy update(final Hierarchy hierarchyToUpdate) {
        checkNotNull(hierarchyToUpdate, "Argument[hierarchyToUpdate] must not be null");

        mongoOperations.save(hierarchyToUpdate);
        logger.info("Updated [{}] hierarchy", hierarchyToUpdate.getName());
        return hierarchyToUpdate;
    }

    public void delete(final String hierarchyIdToDelete) {
        checkNotNull(hierarchyIdToDelete, "Argument[hierarchyIdToDelete] must not be null");

        mongoOperations.remove(Query.query(Criteria.where("_id").is(hierarchyIdToDelete)), Hierarchy.class);
    }

    public void setMongoOperations(final MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
}
