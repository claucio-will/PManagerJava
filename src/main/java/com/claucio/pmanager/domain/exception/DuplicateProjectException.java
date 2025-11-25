package com.claucio.pmanager.domain.exception;

import com.claucio.pmanager.infrastructure.exception.RequestException;

public class DuplicateProjectException extends RequestException {

    public DuplicateProjectException(String projectId) {
        super("DuplicateProject", "A Project with the name already exists: " + projectId);

    }
}
