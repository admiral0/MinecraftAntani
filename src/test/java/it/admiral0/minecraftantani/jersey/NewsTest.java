package it.admiral0.minecraftantani.jersey;

import it.admiral0.minecraftantani.MinecraftAntaniSettings;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author admiral0
 */
public class NewsTest {
    private final MinecraftAntaniSettings settings;
    private final News news;

    public NewsTest() {
        settings = new MinecraftAntaniSettings("localhost", 8000, "/");
        MinecraftAntaniSettings.setInstance(settings);
        news = new News();
    }
    
    @Test
    public void getNewsNoNews() throws IOException{
        assertEquals(news.getNews(null), "");
    }
    
    @Test
    public void getNewsHtml() throws IOException{
        File f = new File("news.html");
        f.createNewFile();
        PrintWriter wr = new PrintWriter(f);
        wr.print("{{version}}");
        wr.close();
        String ver = "testasdfsdafa";
        assertEquals(news.getNews(ver), ver);
        f.delete();
    }
    
}
