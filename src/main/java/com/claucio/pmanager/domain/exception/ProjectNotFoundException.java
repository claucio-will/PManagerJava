package com.claucio.pmanager.domain.exception;

import com.claucio.pmanager.infrastructure.exception.RequestException;

public class ProjectNotFoundException extends RequestException {

    public ProjectNotFoundException(String projectId) {
        super("ProjectNotFound","Project Not Found: " + projectId );

    }
}
