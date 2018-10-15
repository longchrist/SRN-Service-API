package com.srn.api.model.dto;

public class SrnErrorDto extends BaseDto {
    public static String ERROR_MESSAGE_FORBIDDEN = "Access is not allowed!";
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}