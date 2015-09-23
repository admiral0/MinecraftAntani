/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.admiral0.minecraftantani.jersey;

import it.admiral0.minecraftantani.MinecraftAntaniSettings;
import it.admiral0.minecraftantani.PackageConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author admiral0
 */
public class HomeTest {
    private final Home instance;
    private final MinecraftAntaniSettings settings;
    
    public HomeTest(){
        settings = new MinecraftAntaniSettings("localhost", 8000, "/");
        MinecraftAntaniSettings.setInstance(settings);
        instance = new Home();
    }
    
    @Test
    public void testIndex(){
        assertEquals(instance.getIndex().getTemplateName(),"/index.mustache");
    }
    
    @Test
    public void testLauncher() throws IOException{
        File f = new File("launcher_client_generated.jar");
        try {
            assertTrue(instance.getLauncher() instanceof FileInputStream);
            assertTrue(f.exists());
            ZipFile zf = new ZipFile(f);
            ZipEntry ze = zf.getEntry("com/skcraft/launcher/launcher.properties");
            Properties props = new Properties();
            assertTrue(ze != null);
            props.load(zf.getInputStream(ze));
            assertEquals(props.getProperty("version"), PackageConstants.VERSION);
            assertEquals(props.getProperty("newsUrl"), settings.getExternalRoot() + "news.html?v=%s");
            assertEquals(props.getProperty("packageListUrl"), settings.getExternalRoot() + "packages.json?v=%s");
            assertEquals(props.getProperty("selfUpdateUrl"), settings.getExternalRoot() + "launcher.json");
            assertTrue(instance.getLauncher() instanceof FileInputStream);
        } finally {
           f.deleteOnExit();
        }
    }
    
}
