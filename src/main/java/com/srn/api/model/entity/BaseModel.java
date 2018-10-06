package com.srn.api.model.entity;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public abstract class BaseModel<T> implements Serializable {

    public abstract T toDto();

    public String toString() {
        String jsonString = "";

        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(this);
        }catch (JsonGenerationException exJsonGeneration) {
            exJsonGeneration.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}