package it.admiral0.minecraftantani.server;

import com.vaadin.server.VaadinServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.ServletMapping;

/**
 * Manages the lifecycle for undertow server
 */
public class JettyThread extends Thread {
    @Override
    public void run() {
        Server server = new Server(8000);
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(handler);
        ServletHolder holder = new ServletHolder("Vaadin",VaadinServlet.class);
        holder.setInitParameter("UI","it.admiral0.minecraftantani.server.ServerUi");
        handler.addServlet(holder, "/*");
        try {
            server.start();
            server.join();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
