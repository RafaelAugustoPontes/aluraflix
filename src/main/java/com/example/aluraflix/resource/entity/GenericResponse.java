package com.example.aluraflix.resource.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
public class GenericResponse<T> {

    private boolean isInternalError;
    private boolean isNotFound;
    private boolean isCreated;
    private boolean isOk;

    @Getter
    private T object;

    public ResponseEntity<T> generate() {
        HttpStatus status = identifyStatus();
        return ResponseEntity.status(status).body(object);
    }

    private HttpStatus identifyStatus() {
        if (isInternalError)
            return HttpStatus.INTERNAL_SERVER_ERROR;
        if (isNotFound)
            return HttpStatus.NOT_FOUND;
        if (isCreated)
            return HttpStatus.CREATED;
        if (isOk)
            return HttpStatus.OK;
        throw new IllegalArgumentException("Response was not set");
    }

}
