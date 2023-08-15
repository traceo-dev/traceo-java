package org.traceo.sdk;

/**
 * An item creating after parsing each trace from the stacktrace.
 */
public class TraceoTrace {
    /**
     * Filename in which exception occur in this trace.
     */
    private String filename;

    /**
     * Function name in which exception occur in this trace.
     */
    private String function;

    /**
     * Line number in which exception occur in file for this trace.
     */
    private int lineNo;

    /**
     * Column number in which exception occur in file for this trace.
     */
    private int columnNo;

    /**
     * Specifies whether the exception occurred inside the owner's code or in an external library.
     */
    private boolean isInternal;

    /**
     * Absolute path to the file with exception.
     */
    private String absPath;

    /**
     * File extension.
     */
    private String extension;

    /**
     * Code context for the single line in which exception occur in this file.
     */
    private String code;

    /**
     * Code context for the five lines before exception line.
     */
    private String[] preCode;

    /**
     * Code context for the five lines after exception line.
     */
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
