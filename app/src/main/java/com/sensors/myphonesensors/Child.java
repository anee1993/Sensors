package com.sensors.myphonesensors;

/**
 * Created by MobPsycho100 on 16-04-2017.
 */

public class Child {

    public Child(){
    }
    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private String vendor;
    private String version;
}
