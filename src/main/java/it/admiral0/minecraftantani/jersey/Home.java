package it.admiral0.minecraftantani.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.glassfish.jersey.server.mvc.Viewable;

/**
 * The home page
 * @author admiral0
 */
@Path("/")
public class Home {
    @GET
    public Viewable getIndex(){
        return new Viewable("/index.mustache");
    }
}
