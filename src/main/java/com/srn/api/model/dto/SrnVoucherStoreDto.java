package com.srn.api.model.dto;/*
 Author: vikraa
 created: 1/5/19 - 2:07 PM
*/

public class SrnVoucherStoreDto extends BaseDto {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}