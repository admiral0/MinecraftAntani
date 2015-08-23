package it.admiral0.minecraftantani;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import it.admiral0.minecraftantani.server.JettyThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = MinecraftAntani.MODID, version = MinecraftAntani.VERSION)
public class MinecraftAntani
{
    public static final String MODID = "minecraftantani";
    public static final String VERSION = "0.1";
    public static final Logger LOGGER = LogManager.getLogger();

    private boolean disabled = false;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        if(event.getSide().isClient()){
            disabled = true;
            LOGGER.info("The mod " + MODID + " is a server mod for now. It will do nothing on the client");
            return;
        }

        JettyThread thread = new JettyThread();
        thread.start();
    }
}
