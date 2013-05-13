package com.zemiak.books.vaadin;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.vaadin.addon.touchkit.extensions.Html5InputSettings;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.widgetsetutils.ConnectorBundleLoaderFactory;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.Connect.LoadStyle;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import java.util.LinkedList;
import java.util.List;

public class BooksLoadFactory extends ConnectorBundleLoaderFactory {
    private static final List<Class> eagerComponents = new LinkedList<>();
    static {
        eagerComponents.add(UI.class);
        eagerComponents.add(NavigationManager.class);
        eagerComponents.add(Toolbar.class);
        eagerComponents.add(VerticalComponentGroup.class);
        eagerComponents.add(CssLayout.class);
        eagerComponents.add(NavigationButton.class);
    }
    
    private static final List<Class> deferredComponents = new LinkedList<>();
    static {
        deferredComponents.add(Link.class);
        deferredComponents.add(Label.class);
        deferredComponents.add(FileDownloader.class);
        deferredComponents.add(TextField.class);
        deferredComponents.add(Html5InputSettings.class);
        deferredComponents.add(Button.class);
    }

    @Override
    protected LoadStyle getLoadStyle(JClassType connectorType) {
        Connect annotation = connectorType.getAnnotation(Connect.class);
        Class componentClass = annotation.value();

        // Load eagerly marked connectors before the first view is shown
        if (eagerComponents.contains(componentClass)) {
            return LoadStyle.EAGER;
        }
        
        // Load deffered marked after the first view is shown
        if (deferredComponents.contains(componentClass)) {
            return LoadStyle.EAGER;
        }

        // All other components should be lazy
        return LoadStyle.LAZY;
    }
}

