package com.tahaakocer.ybdizaynavize.exception;

public class S3DeleteImageException extends RuntimeException {
    public S3DeleteImageException(String errorDeletingImage) {
        super(errorDeletingImage);
    }
}
