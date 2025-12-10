package com.claucio.pmanager.domain.exception;

import com.claucio.pmanager.infrastructure.exception.RequestException;

public class DuplicateMemberException extends RequestException {

    public DuplicateMemberException(String email) {
        super("DuplicateMember", "A Member with the name already exists: " + email);

    }
}
