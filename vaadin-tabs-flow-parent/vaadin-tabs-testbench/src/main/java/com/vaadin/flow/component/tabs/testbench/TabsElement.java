/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.tabs.testbench;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

/**
 * A TestBench element representing a <code>&lt;vaadin-tabs&gt;</code> element.
 */
@Element("vaadin-tabs")
public class TabsElement extends TestBenchElement {

    /**
     * Selects the tab with the given index.
     *
     * @param selectedTab
     *            the index of the tab to select
     */
    public void setSelectedTabIndex(int selectedTab) {
        setProperty("selected", selectedTab);
    }

    /**
     * Gets the index of the currently selected tab.
     *
     * @return the index of the currenly selected tab
     */
    public int getSelectedTabIndex() {
        return getPropertyInteger("selected");
    }

    /**
     * Gets the tab element for the currently selected tab.
     *
     * @return a tab element for the currently selected tab
     */
    public TabElement getSelectedTabElement() {
        return ((TestBenchElement) executeScript(
                "return arguments[0].children[arguments[0].selected];", this))
                .wrap(TabElement.class);
    }

    /**
     * Gets the tab element for the tab with the given text.
     *
     * @param text
     *            the text to look for in the tabs
     * @return the first tab element which matches the given text
     * @throws NoSuchElementException
     *             if no match was found
     */
    public TabElement getTabElement(String text) throws NoSuchElementException {
        int index = getTab(text);
        if (index == -1) {
            throw new NoSuchElementException(
                    "No tab with text '" + text + "' found");
        }
        return $(TabElement.class).get(index);
    }

    /**
     * Gets the index of the tab with the given text.
     *
     * @param text
     *            the text to look for in the tabs
     * @return the index of the first tab element which matches the given text
     *         or -1 if no match was found
     */
    public int getTab(String text) {
        List<TestBenchElement> children = getPropertyElements("children");
        for (int i = 0; i < children.size(); i++) {
            String tabText = children.get(i).wrap(TabElement.class).getText();
            if (text.equals(tabText)) {
                return i;
            }
        }
        return -1;
    }

}
