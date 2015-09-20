/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.admiral0.minecraftantani.jersey;

import it.admiral0.minecraftantani.MinecraftAntaniSettings;
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
}
