package com.traceo.sdk;

/**
 * Runtime platform information on which SDk is running.
 */
public class TraceoRuntimePlatform {
    private String arch;

    private String name;

    private String release;

    private String jvmName;

    private String jvmVersion;

    private String javaVersion;

    private String javaVendor;

    public TraceoRuntimePlatform() {
        this(
                System.getProperty("os.arch"),
                System.getProperty("os.name"),
                System.getProperty("os.release"),
                System.getProperty("java.vm.name"),
                System.getProperty("java.vm.version"),
                System.getProperty("java.version"),
                System.getProperty("java.vendor")
        );
    }

    public TraceoRuntimePlatform(String arch, String name, String release, String jvmName, String jvmVersion, String javaVersion, String javaVendor) {
        this.arch = arch;
        this.name = name;
        this.release = release;
        this.jvmName = jvmName;
        this.jvmVersion = jvmVersion;
        this.javaVersion = javaVersion;
        this.javaVendor = javaVendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJvmName() {
        return jvmName;
    }

    public void setJvmName(String jvmName) {
        this.jvmName = jvmName;
    }

    public String getJvmVersion() {
        return jvmVersion;
    }

    public void setJvmVersion(String jvmVersion) {
        this.jvmVersion = jvmVersion;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getJavaVendor() {
        return javaVendor;
    }

    public void setJavaVendor(String javaVendor) {
        this.javaVendor = javaVendor;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getPlatform() {
        return name;
    }

    public void setPlatform(String platform) {
        this.name = platform;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
