package com.claucio.pmanager.domain.exception;

import com.claucio.pmanager.infrastructure.exception.RequestException;

public class InvalidProjectStatusException extends RequestException {

    public InvalidProjectStatusException(String statusStr) {
        super("InvalidProjectStatus","Invalid project status: " + statusStr );

    }
}
