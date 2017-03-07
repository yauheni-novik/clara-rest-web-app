package com.epam.clara.restwebapp.beans;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Yauheni_Novik on 3/7/2017.
 */
@Document(collection = Hierarchy.COLLECTION_NAME)
public class Hierarchy {
    public static final String COLLECTION_NAME = "hierarchies";

    @Id
    private String _id;
    private String name;
    private String description;
    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
