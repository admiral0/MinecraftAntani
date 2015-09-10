package it.admiral0.minecraftantani.server;

import javax.ws.rs.Path;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.reflections.Reflections;

/**
 * Manages the lifecycle for undertow server
 */
public class JettyThread extends Thread {

    private final String host;
    private final String webRoot;
    private final int port;

    public JettyThread(String host, String webRoot, int port) {
        this.host = host;
        this.webRoot = webRoot;
        this.port = port;
    }
    
    @Override
    public void run() {
        try {
        Server server = new Server(port);
            ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            handler.setContextPath("/");
            
            Reflections refl = new Reflections("it.admiral0.minecraftantani.jersey");
            ResourceConfig conf = new ResourceConfig(refl.getTypesAnnotatedWith(Path.class));
            conf.register(org.glassfish.jersey.server.mvc.mustache.MustacheMvcFeature.class);
            
            ServletHolder holder = new ServletHolder(new ServletContainer(conf));
            handler.addServlet(holder, webRoot + "*");
            server.setHandler(handler);
        
            server.start();
            server.join();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
