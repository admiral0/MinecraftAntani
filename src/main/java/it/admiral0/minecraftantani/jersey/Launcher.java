
package it.admiral0.minecraftantani.jersey;

import it.admiral0.minecraftantani.MinecraftAntaniSettings;
import it.admiral0.minecraftantani.PackageConstants;
import it.admiral0.minecraftantani.beans.LauncherBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author admiral0
 */
@Path("/launcher.json")
public class Launcher {
    @GET @Produces("application/json")
    public LauncherBean get(){
        return new LauncherBean(
                PackageConstants.VERSION,
                MinecraftAntaniSettings.getInstance().getExternalRoot() + "launcher.jar"
        );
    }
}
