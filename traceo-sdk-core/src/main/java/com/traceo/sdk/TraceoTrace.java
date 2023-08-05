package com.traceo.sdk;

public class TraceoTrace {
    private String filename;

    private String function;

    private int lineNo;

    private int columnNo;

    private boolean isInternal;

    private String absPath;

    private String extension;

    private String code;

    private String[] preCode;

    private String[] postCode;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public int getColumnNo() {
        return columnNo;
    }

    public void setColumnNo(int columnNo) {
        this.columnNo = columnNo;
    }

    public boolean isInternal() {
        return isInternal;
    }

    public void setInternal(boolean internal) {
        isInternal = internal;
    }

    public String getAbsPath() {
        return absPath;
    }

    public void setAbsPath(String absPath) {
        this.absPath = absPath;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getPreCode() {
        return preCode;
    }

    public void setPreCode(String[] preCode) {
        this.preCode = preCode;
    }

    public String[] getPostCode() {
        return postCode;
    }

    public void setPostCode(String[] postCode) {
        this.postCode = postCode;
    }
}
