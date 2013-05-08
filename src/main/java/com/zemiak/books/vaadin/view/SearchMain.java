package com.zemiak.books.vaadin.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.zemiak.books.vaadin.NavManager;
import com.zemiak.books.vaadin.NavToolbar;
import org.vaadin.actionbuttontextfield.ActionButtonTextField;
import org.vaadin.actionbuttontextfield.widgetset.client.ActionButtonType;

public class SearchMain extends NavigationView implements Component {
    NavManager manager;
    Layout form = null;
    final TextField searchField = new TextField();
    
    public SearchMain(NavManager manager) {
        setCaption("Books");

        this.manager = manager;

        this.setToolbar(new NavToolbar(manager));
        refresh();
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        searchField.focus();
    }

    private void refresh() {
        form = new CssLayout();
        setContent(form);
        
        PropertysetItem item = new PropertysetItem();
        item.addItemProperty("name", new ObjectProperty<>(""));
        
        VerticalComponentGroup group = new VerticalComponentGroup("Search");
        
        ActionButtonTextField searchButtonTextField = ActionButtonTextField.extend(searchField);
        searchButtonTextField.setActionButtonType(ActionButtonType.ACTION_SEARCH);
        searchButtonTextField.addClickListener(new ActionButtonTextField.ClickListener() {
            @Override
            public void buttonClick(ActionButtonTextField.ClickEvent clickEvent) {
                getNavigationManager().navigateTo(new SearchPage(searchField.getValue(), manager));
            }
        });
        
        searchField.setInputPrompt("Search...");
        searchField.setWidth("90%");
        searchField.setImmediate(true);
        searchField.focus();
        group.addComponent(searchField);

        Button button = new Button();
        button.setCaption("Search");
        button.setVisible(false);
        button.setClickShortcut(KeyCode.ENTER);
        group.addComponent(button);
        
        FieldGroup binder = new FieldGroup(item);
        binder.bind(searchField, "name");
        
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getNavigationManager().navigateTo(new SearchPage(searchField.getValue(), manager));
            }
        });

        form.addComponent(group);
    }
}
