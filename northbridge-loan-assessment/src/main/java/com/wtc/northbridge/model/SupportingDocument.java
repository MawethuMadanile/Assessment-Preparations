package com.wtc.northbridge.model;

public class SupportingDocument {

    private final String fileName;
    private final String extension;
    private final double sizeInMb;

    public SupportingDocument(String fileName, String extension, double sizeInMb) {
        this.fileName = fileName;
        this.extension = extension;
        this.sizeInMb = sizeInMb;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }

    public double getSizeInMb() {
        return sizeInMb;
    }
}
