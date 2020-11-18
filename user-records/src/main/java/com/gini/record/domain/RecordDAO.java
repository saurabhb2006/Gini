package com.gini.record.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

/**
 * Item Data Access Object Object
 */
@Entity
@Table(name = "RECORDS")
public class RecordDAO {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID")
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID id;
    @Size(min=1)
    @Column(name = "USERID", unique = true)
    private String userId;
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Size(min=5)
    @Column(name = "DESCRIPTION")
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_TIME")
    @CreationTimestamp
    private Date date;

    public RecordDAO() {
    }

    public RecordDAO(@Size(min = 1) String userId, String type, @Size(min = 5) String description) {
         this.userId = userId;
        this.type = type;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    @JsonIgnore
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Records{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
