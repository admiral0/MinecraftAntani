package it.admiral0.minecraftantani.server;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
@Push
public class ServerUi extends UI{
    @Override
    protected void init(VaadinRequest request) {
        setContent(new Label("HELLO ANTANI"));
    }
}
