package com.zemiak.books.ui.tablet.view;

import com.vaadin.addon.touchkit.extensions.Html5InputSettings;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Book;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.inject.Inject;

@CDIView("searchTablet")
public class SearchTablet extends ViewAbstractTablet {
    Layout form = null;
    final TextField searchField = new TextField();
    boolean initialized = false;
    
    @Inject
    Collection col;
    
    @Inject
    SearchResultsTablet resultsView;
    
    @Inject
    DateFilterResultsTablet dateView;
    
    @Inject
    SourceResultsTablet sourceView;
    
    public SearchTablet() {
    }
    
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        setCaption("Search");
        
        if (initialized) {
            searchField.focus();
            return;
        }
        
        refresh();
        initialized = true;
    }

    private void refresh() {
        form = new CssLayout();
        setContent(form);

        initSearchGroup();
        initFilterGroup();
    }

    private void initSearchGroup() throws FieldGroup.BindException {
        PropertysetItem item = new PropertysetItem();
        item.addItemProperty("name", new ObjectProperty<>(""));
        
        VerticalComponentGroup group = new VerticalComponentGroup("Search");
        
        final Html5InputSettings html5InputSettings = new Html5InputSettings(
                searchField);
        html5InputSettings.setProperty("type", "search");
        html5InputSettings.setProperty("autocomplete", "off");
        html5InputSettings.setProperty("autocorrect", "off");
        html5InputSettings.setProperty("autocapitalize", "off");
        html5InputSettings.setProperty("placeholder", "Search...");
        
        searchField.setImmediate(true);
        searchField.focus();
        group.addComponent(searchField);

        Button button = new Button();
        button.setCaption("Search");
        button.setVisible(false);
        button.setClickShortcut(KeyCode.ENTER);
        group.addComponent(button);
        
        NavigationButton navButton = new NavigationButton();
        navButton.setCaption("Search");
        navButton.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                resultsView.setText(searchField.getValue());
                getNavManager().navigateTo(resultsView);
            }
        });
        group.addComponent(navButton);
        
        FieldGroup binder = new FieldGroup(item);
        binder.bind(searchField, "name");
        
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                resultsView.setText(searchField.getValue());
                getNavManager().navigateTo(resultsView);
            }
        });

        form.addComponent(group);
    }

    private void initFilterGroup() {
        VerticalComponentGroup group = new VerticalComponentGroup("Filters");
        
        NavigationButton navButton = new NavigationButton();
        navButton.setCaption("Last 6 months");
        navButton.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                Date to = new Date();
                GregorianCalendar cal = new GregorianCalendar();
                
                cal.setTime(to);
                cal.add(Calendar.MONTH, -6);
                Date from = cal.getTime();
                
                dateView.setDateInterval(from, to);
                getNavManager().navigateTo(dateView);
            }
        });
        group.addComponent(navButton);
        
        navButton = new NavigationButton();
        navButton.setCaption("6 to 12 months");
        navButton.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                GregorianCalendar cal = new GregorianCalendar();
                
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, -6);
                
                Date to = cal.getTime();
                
                cal.add(Calendar.MONTH, -6);
                
                Date from = cal.getTime();
                
                dateView.setDateInterval(from, to);
                getNavManager().navigateTo(dateView);
            }
        });
        group.addComponent(navButton);
        
        navButton = new NavigationButton();
        navButton.setCaption("From PalmKnihy");
        navButton.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                sourceView.setSource(Book.BookSource.PALMKNIHY);
                getNavManager().navigateTo(sourceView);
            }
        });
        group.addComponent(navButton);
        
        navButton = new NavigationButton();
        navButton.setCaption("From Martinus");
        navButton.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                sourceView.setSource(Book.BookSource.MARTINUS);
                getNavManager().navigateTo(sourceView);
            }
        });
        group.addComponent(navButton);
        
        navButton = new NavigationButton();
        navButton.setCaption("From Kosmas");
        navButton.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                sourceView.setSource(Book.BookSource.KOSMAS);
                getNavManager().navigateTo(sourceView);
            }
        });
        group.addComponent(navButton);
        
        form.addComponent(group);
    }
}
