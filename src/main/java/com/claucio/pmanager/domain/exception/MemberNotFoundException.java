package com.claucio.pmanager.domain.exception;

import com.claucio.pmanager.infrastructure.exception.RequestException;

public class MemberNotFoundException extends RequestException {

    public MemberNotFoundException(String memberId) {
        super("MemberNotFound","Member Not Found: " + memberId );

    }
}
