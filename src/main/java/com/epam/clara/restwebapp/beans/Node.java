package com.epam.clara.restwebapp.beans;

import com.google.common.base.Objects;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

import static com.google.common.base.Strings.emptyToNull;

@Document(collection = Node.COLLECTION_NAME)
public class Node implements Serializable {
    public static final String COLLECTION_NAME = "offices";

    @Id
    private String _id;
    private String title;
    private String parent;
    private Metadata metadata;

    public String get_id() {
      return _id;
    }

    public void set_id(final String _id) {
      this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = emptyToNull(title);
    }

    public Metadata getMetadata() {
      return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }
        Node that = (Node) o;
        return Objects.equal(this.title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
          .add("title", title)
          .add("metadata", metadata)
          .toString();
    }
}
