package com.shang.common.exception;

public class ParserException extends RuntimeException {
    public ParserException() {
    }

    public ParserException(String message) {
        super("ParserException:" + message);
    }

    public ParserException(String message, Throwable cause) {
        super("ParserException:" + message, cause);
    }

    public ParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super("ParserException:" + message, cause, enableSuppression, writableStackTrace);
    }
    public static class JsonException extends ParserException{
        public JsonException() {
        }

        public JsonException(String message) {
            super(message);
        }
    }
}
