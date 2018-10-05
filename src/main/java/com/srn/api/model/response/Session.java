package com.srn.api.model.response;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srn.api.model.entity.SrnDevice;

public class Session {

    private String sessionId;

    public Session(SrnDevice device) {
        this.sessionId = "this-is-session-device";
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
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