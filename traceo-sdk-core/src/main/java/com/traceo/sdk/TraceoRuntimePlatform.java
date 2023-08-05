package com.traceo.sdk;

public class TraceoRuntimePlatform {
    private String arch;

    private String platform;

    private String release;

    public TraceoRuntimePlatform() {
        this(System.getProperty("os.arch"), System.getProperty("os.name"), System.getProperty("os.release"));
    }

    public TraceoRuntimePlatform(String arch, String name, String release) {
        this.arch = arch;
        this.platform = name;
        this.release = release;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
