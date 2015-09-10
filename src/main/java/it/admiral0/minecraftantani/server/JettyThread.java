package it.admiral0.minecraftantani.server;

import org.eclipse.jetty.server.Server;

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
        Server server = new Server(port);
        /*ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(handler);
        ServletHolder holder = new ServletHolder("Vaadin",VaadinServlet.class);
        holder.setInitParameter("UI","it.admiral0.minecraftantani.server.ServerUi");
        handler.addServlet(holder, "/*");*/
        try {
            server.start();
            server.join();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
