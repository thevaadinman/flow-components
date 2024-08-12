/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.grid.it;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.component.grid.testbench.GridElement;
import com.vaadin.flow.component.grid.testbench.GridTHTDElement;
import com.vaadin.flow.testutil.TestPath;
import com.vaadin.tests.AbstractComponentIT;

@TestPath("vaadin-grid/template-renderer-event-handler")
public class TemplateRendererEventHandlerIT extends AbstractComponentIT {

    @Test
    public void eventHandlersWorkOnReattach() {
        open();

        waitUntil(driver -> $(GridElement.class).first().getRowCount() > 0);

        findElement(By.id("show-hide")).click();
        Assert.assertTrue(!isElementPresent(By.tagName("vaadin-grid")));

        findElement(By.id("show-hide")).click();
        GridElement grid = $(GridElement.class).first();
        waitUntil(driver -> grid.getRowCount() > 0);

        GridTHTDElement cell = grid.getCell(0, 0);
        cell.$("button").first().click();

        List<WebElement> clickedPersons = findElements(
                By.className("clicked-person"));
        Assert.assertEquals(1, clickedPersons.size());
        Assert.assertEquals("John Doe", clickedPersons.get(0).getText());
    }
}
