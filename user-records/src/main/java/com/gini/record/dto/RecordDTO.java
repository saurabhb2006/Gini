package com.gini.record.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class RecordDTO {

    @Size(min=1)
    private String userId;
    private String type;
    @Size(min=1)
    private String description;

    public RecordDTO(@Size(min = 1) String userId, String type, @Size(min = 1) String description) {
        this.userId = userId;
        this.type = type;
        this.description = description;
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
}
