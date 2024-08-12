/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.tabs.tests;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

@Route("vaadin-tabs/selected-tab")
public class SelectedTabPage extends Div {

    public SelectedTabPage() {
        Tabs tabs = new Tabs();
        Tab tab1 = new Tab("foo");
        tabs.add(tab1);
        Tab tab2 = new Tab("bar");
        tabs.add(tab2);
        tab2.setId("second");

        Tab tab3 = new Tab("baz");
        tabs.add(tab3);
        tab3.setId("third");
        tab3.setEnabled(false);

        NativeButton button = new NativeButton("Show tabs selection", event -> {
            Div div = new Div();
            div.addClassName("first");
            div.setText("The first tab is selected: " + tab1.isSelected());
            add(div);
            div = new Div();
            div.addClassName("second");
            div.setText("The second tab is selected: " + tab2.isSelected());
            add(div);
        });

        button.setId("show-selection");

        NativeButton delete = new NativeButton("Delete selected tab",
                event -> tabs.remove(tabs.getSelectedTab()));
        delete.setId("delete");

        NativeButton addFirst = new NativeButton("Add new tab as the first",
                event -> tabs.addComponentAsFirst(new Tab("baz")));
        addFirst.setId("add-first");

        NativeButton setSelectedIndex = new NativeButton("setSelectedIndex(1)",
                event -> tabs.setSelectedIndex(1));
        setSelectedIndex.setId("set-selected-index");

        NativeButton setSelectedTab = new NativeButton("setSelectedTab(tab2)",
                event -> tabs.setSelectedTab(tab2));
        setSelectedTab.setId("set-selected-tab");

        NativeButton deleteFirst = new NativeButton("Delete first tab",
                event -> tabs.remove(tabs.getChildren().findFirst().get()));
        deleteFirst.setId("delete-first");

        NativeButton addFirstWithElementAPI = new NativeButton(
                "Add new tab as the first using Element API",
                event -> tabs.getElement().insertChild(0,
                        new Tab("asdf").getElement()));
        addFirstWithElementAPI.setId("add-first-with-element-api");

        tabs.addSelectedChangeListener(event -> addEventMessage(
                tabs.getSelectedTab() == null ? "null"
                        : tabs.getSelectedTab().getLabel(),
                event.isFromClient()));

        NativeButton unselect = new NativeButton("Unselect",
                event -> tabs.setSelectedTab(null));
        unselect.setId("unselect");

        NativeButton unselectWithIndex = new NativeButton("Unselect with index",
                event -> tabs.setSelectedIndex(-1));
        unselectWithIndex.setId("unselect-with-index");

        add(tabs, button, delete, deleteFirst, addFirst, addFirstWithElementAPI,
                setSelectedIndex, setSelectedTab, unselect, unselectWithIndex);
    }

    private void addEventMessage(String tabLabel, boolean isFromClient) {
        Paragraph message = new Paragraph(
                tabLabel + (isFromClient ? " client" : " server"));
        message.setClassName("selection-event");
        add(message);
    }
}
