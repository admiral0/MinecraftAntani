package it.admiral0.minecraftantani;

/**
 * 
 * @author admiral0
 */
public class MinecraftAntaniSettings {
    private final String host;
    private final int port;
    private final String webroot;
    
    private static MinecraftAntaniSettings _instance=null;

    public MinecraftAntaniSettings(String host, int port, String webroot) {
        this.host = host;
        this.port = port;
        this.webroot = webroot;
    }
    
    public static void setInstance(MinecraftAntaniSettings s){
        _instance = s;
    }
    
    public static MinecraftAntaniSettings getInstance(){
        assert _instance != null;
        return _instance;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getWebroot() {
        return webroot;
    }
    
    public String getExternalRoot() {
        return "http://" + host + Integer.toString(port) + "/" + webroot + "/";
    }
    
}
