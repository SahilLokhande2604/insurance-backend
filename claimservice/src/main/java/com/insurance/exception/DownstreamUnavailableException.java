package com.insurance.exception;

import com.insurance.enums.DownstreamService;

public class DownstreamUnavailableException extends RuntimeException {
    private final DownstreamService service;
    private final int statusCode;

    public DownstreamUnavailableException(DownstreamService service, String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.service = service;
        this.statusCode = statusCode;
    }

    public DownstreamService getService() { return service; }
    public int getStatusCode() { return statusCode; }
}
