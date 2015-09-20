package it.admiral0.minecraftantani.jersey;

import it.admiral0.minecraftantani.MinecraftAntaniSettings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.server.mvc.Viewable;

/**
 * The home page
 *
 * @author admiral0
 */
@Path("/")
public class Home {

    @GET
    public Viewable getIndex() {
        return new Viewable("/index.mustache");
    }

    @Path("/download/launcher.jar")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream getLauncher() throws IOException {
        File gen = new File("launcher_client_generated.jar");
        if (!gen.exists()) {
            generateLauncher(gen);
        }
        return new FileInputStream(gen);
    }
    
    private void generateLauncher(File dest) throws IOException{
        ZipInputStream zipStream = new ZipInputStream(Home.class.getResourceAsStream("/launcher.jar"));
        final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));
        ZipEntry entryIn;
        while ((entryIn = zipStream.getNextEntry()) != null) {
            if (!entryIn.getName().equalsIgnoreCase("com/skcraft/launcher/launcher.properties")) {
                zos.putNextEntry(entryIn);
                byte[] buf = new byte[1024];
                int len;
                while ((len = (zipStream.read(buf))) > 0) {
                    zos.write(buf, 0, len);
                }
            } else {
                zos.putNextEntry(new ZipEntry("com/skcraft/launcher/launcher.properties"));
                Properties p = new Properties();
                MinecraftAntaniSettings s = MinecraftAntaniSettings.getInstance();
                p.load(Home.class.getResourceAsStream("/settings/default.properties"));
                p.setProperty("version", "1.0");
                p.setProperty("newsUrl", s.getExternalRoot() + "news.html?v=%s" );
                p.setProperty("packageListUrl", s.getExternalRoot() + "packages.json?v=%s" );
                p.setProperty("selfUpdateUrl", s.getExternalRoot() + "launcher.json");
                
                byte[] buf = new byte[1024];
                int len;
                p.store(zos, null);
            }
            zos.closeEntry();
        }
        zos.close();
    }
}
