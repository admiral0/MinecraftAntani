/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.admiral0.minecraftantani.jersey;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author admiral0
 */
@Path("/news.html")
public class News {
    
    @GET @Produces("text/html")
    public String getNews(
            @DefaultValue("") @QueryParam("version")
            String version
    ) throws IOException{
        File html = new File("news.html");
        if(!html.exists() && !html.canRead())
            return "";
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile(new FileReader(html),"news");
        StringWriter out = new StringWriter();
        HashMap<String,Object> scope = new HashMap<String, Object>();
        scope.put("version", version);
        m.execute(out, scope);
        return out.toString();
    }
    
}
