package com.tahaakocer.ybdizaynavize.exception.product;

public class S3DeleteImageException extends RuntimeException {
    public S3DeleteImageException(String errorDeletingImage) {
        super(errorDeletingImage);
    }
}
