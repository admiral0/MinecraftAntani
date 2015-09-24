/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.admiral0.minecraftantani.jersey;

import it.admiral0.minecraftantani.MinecraftAntaniSettings;
import it.admiral0.minecraftantani.PackageConstants;
import it.admiral0.minecraftantani.beans.LauncherBean;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author admiral0
 */
public class LauncherTest {
    private MinecraftAntaniSettings settings;
    private Launcher instance;

    public LauncherTest() {
        settings = new MinecraftAntaniSettings("localhost", 8000, "/");
        MinecraftAntaniSettings.setInstance(settings);
        instance = new Launcher();
    }
    
    @Test
    public void testLauncherVer(){
        LauncherBean res = instance.get();
        assertEquals(res.getVersion(), PackageConstants.VERSION);
        assertEquals(res.getUrl(), settings.getExternalRoot() + "launcher.jar");
    }
}
