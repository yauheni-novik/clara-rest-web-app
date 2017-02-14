package com.epam.clara.restwebapp.beans;

import com.google.common.base.Objects;

import java.util.List;

public class Metadata {

    private List<String> users;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("users", users)
            .toString();
    }
}
