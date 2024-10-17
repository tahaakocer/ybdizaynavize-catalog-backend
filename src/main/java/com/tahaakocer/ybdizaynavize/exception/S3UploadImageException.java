package com.tahaakocer.ybdizaynavize.exception;

public class S3UploadImageException extends RuntimeException {
    public S3UploadImageException(String errorUploadingImage) {
        super(errorUploadingImage);
    }
}
