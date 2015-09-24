package it.admiral0.minecraftantani.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admiral0
 */
@XmlRootElement
public class LauncherBean {
    private final String version;
    private final String url;

    public LauncherBean(String version, String url) {
        this.version = version;
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public String getUrl() {
        return url;
    }
    
    
}
