package com.claucio.pmanager.infrastructure.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RestError {

    private final String errorCode;
    private final String errorMessage;
    private final List<String> detail;
    private final int status;
    private final String path;




}
