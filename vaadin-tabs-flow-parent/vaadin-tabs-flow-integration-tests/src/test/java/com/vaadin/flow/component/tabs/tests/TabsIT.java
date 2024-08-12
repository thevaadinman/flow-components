/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.tabs.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.component.tabs.demo.TabsView;
import com.vaadin.tests.ComponentDemoTest;

/**
 * Integration tests for the {@link TabsView}.
 *
 * @author Vaadin Ltd.
 */
public class TabsIT extends ComponentDemoTest {

    @Test
    public void pageGetsDisplayedWhenAssociatedTabIsSelected() {
        WebElement tab3 = layout.findElement(By.id("tab3"));
        WebElement page1 = layout.findElement(By.id("page1"));
        assertFalse(isElementPresent(By.id("page3")));
        assertThat(page1.getCssValue("display"), is("block"));

        scrollIntoViewAndClick(tab3);

        waitUntil(driver -> "true".equals(page1.getAttribute("hidden")));
        WebElement page3 = layout.findElement(By.id("page3"));
        assertThat(page3.getCssValue("display"), is("block"));
    }

    @Test
    public void assertThemeVariant() {
        verifyThemeVariantsBeingToggled();
    }

    @Override
    protected String getTestPath() {
        return "/vaadin-tabs";
    }
}
