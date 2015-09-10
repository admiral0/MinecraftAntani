package it.admiral0.minecraftantani;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import it.admiral0.minecraftantani.server.JettyThread;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = MinecraftAntani.MODID, version = MinecraftAntani.VERSION)
public class MinecraftAntani
{
    private static final String CAT_WEBSERVER = "WebServer";
    
    public static final String MODID = "minecraftantani";
    public static final String VERSION = "0.1";
    public static final Logger LOGGER = LogManager.getLogger();

    private boolean disabled = false;
    
    private int port;
    private String webRoot;
    private String host;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        
        Property portProperty = config.get(CAT_WEBSERVER, "port", 8000);
        portProperty.comment = "The port the webserver runs on.\n"
                + "You should set this on a nonstandard port and use a proxy"
                + "to serve the pages";
        port = portProperty.getInt();
        
        Property rootProperty = config.get(CAT_WEBSERVER, "webroot", "/");
        rootProperty.comment = "The root on which the page should be served\n"
                + "The default is to serve from / so it can be served from urls"
                + "like http://example.com/. You could set it to /download/ to "
                + "serve from urls like http://example.com/download/\nIt should"
                + "always start and end with a slash";
        webRoot = rootProperty.getString();
        
        Property hostProperty = config.get(CAT_WEBSERVER, "host", "localhost");
        hostProperty.comment = "This is the most important setting. You should"
                + " change this to your server's IP or domain like 127.0.0.1 or"
                + " myawesomepack.com\nIf you are using https, be sure to"
                + " redirect http requests to https";
        
        host = hostProperty.getString();
        
        config.save();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        if(event.getSide().isClient()){
            disabled = true;
            LOGGER.info("The mod " + MODID + " is a server mod for now. It will do nothing on the client.");
            return;
        }
        
        /* Checking settings */
        if(!webRoot.matches("/(.+/)?")){
            LOGGER.warn("Your webroot setting is wrong. Assuming /");
            webRoot = "/";
        }
        
        if(port < 1024 || port > 65535){
            LOGGER.warn("Valid port interval is 1024 < port < 65535. Will default to 8080");
            port = 8080;
        }
        
        
        LOGGER.info("Starting Jetty server. You can reach it on on http://" + host + ":" + Integer.toString(port) + webRoot + " ..." );
        JettyThread thread = new JettyThread(host,webRoot,port);
        thread.start();
    }
}
