/*
 * Copyright (C) 2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.grid.it;

import com.vaadin.flow.component.grid.testbench.GridElement;
import com.vaadin.tests.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;
import com.vaadin.testbench.TestBenchElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@TestPath("vaadin-grid/grid-details-row")
public class GridDetailsRowIT extends AbstractComponentIT {

    @Test
    public void gridTwoItemsSelectedWhenOpen() {
        open();
        GridElement grid = $(GridElement.class).first();
        // detail configured
        assertAmountOfOpenDetails(grid, 1);

        waitUntil(driver -> grid
                .findElements(By.tagName("flow-component-renderer"))
                .size() == 2, 1);

        // each detail contain a button
        List<WebElement> detailsElement = grid
                .findElements(By.tagName("flow-component-renderer"));

        Assert.assertEquals(2, detailsElement.size());

        assertElementHasButton(detailsElement.get(0), "Person 1");
        assertElementHasButton(detailsElement.get(1), "Person 2");
    }

    @Test
    public void shouldNotThrowOnDetailsClick() {
        open();
        GridElement grid = $(GridElement.class).first();
        grid.getRow(1).getDetails().click(0, 0);
        checkLogsForErrors();
    }

    /**
     * Click on an item, hide the other details
     */
    @Test
    public void gridSelectItem4DisplayDetails() {
        open();
        GridElement grid = $(GridElement.class).first();
        // select row 3
        clickElementWithJs(getRow(grid, 3).findElement(By.tagName("td")));

        waitUntil(driver -> grid
                .findElements(By.tagName("flow-component-renderer"))
                .size() == 3, 1);

        List<WebElement> detailsElement = grid
                .findElements(By.tagName("flow-component-renderer"));
        Assert.assertEquals(3, detailsElement.size());

        // detail on row 0 is not displayed
        assertElementNotDisplayed(detailsElement.get(0));
        // detail on row 1 is not displayed
        assertElementNotDisplayed(detailsElement.get(1));
        // detail on row 3 contains a button
        assertElementHasButton(detailsElement.get(2), "Person 4");
    }

    /**
     * If the details of an item is opened and the item updated then the detail
     * should be updated
     */
    @Test
    @org.junit.Ignore("Unstable test when migrated to mono-repo")
    public void gridUpdateItemUpdateDetails() {
        open();
        GridElement grid = $(GridElement.class).first();
        // select row 3
        clickElementWithJs(getRow(grid, 2).findElement(By.tagName("td")));

        waitUntil(driver -> grid
                .findElements(By.tagName("flow-component-renderer"))
                .size() == 3, 1);

        List<WebElement> detailsElement = grid
                .findElements(By.tagName("flow-component-renderer"));
        Assert.assertEquals(3, detailsElement.size());
        // detail on row 0 is not displayed
        assertElementNotDisplayed(detailsElement.get(0));
        // detail on row 1 is not displayed
        assertElementNotDisplayed(detailsElement.get(1));
        // detail on row 3 contains a button
        assertElementHasButton(detailsElement.get(2), "Person 3");

        WebElement updateButton = findElement(By.id("update-button"));
        updateButton.click();

        waitUntil(driver -> grid
                .findElements(By.tagName("flow-component-renderer"))
                .size() == 3, 1);

        detailsElement = grid
                .findElements(By.tagName("flow-component-renderer"));

        Assert.assertEquals(3, detailsElement.size());

        // detail on row 0 is not displayed
        assertElementNotDisplayed(detailsElement.get(0));
        // detail on row 1 is not displayed
        assertElementNotDisplayed(detailsElement.get(1));
        // detail on row 3 contains a button
        assertElementHasButton(detailsElement.get(2), "Person 3 - updates 1");

    }

    private WebElement getRow(TestBenchElement grid, int row) {
        return grid.$("*").id("items").findElements(By.cssSelector("tr"))
                .get(row);
    }

    private void assertAmountOfOpenDetails(WebElement grid,
            int expectedAmount) {
        waitUntil(driver -> grid.findElements(By.className("row-details"))
                .size() == expectedAmount);
        Assert.assertEquals(expectedAmount,
                grid.findElements(By.className("row-details")).size());
    }

    private void assertElementHasButton(WebElement componentRenderer,
            String content) {

        List<WebElement> children = componentRenderer
                .findElements(By.tagName("vaadin-button"));
        Assert.assertEquals(1, children.size());
        Assert.assertEquals(content, children.get(0).getText());
    }

    private void assertElementNotDisplayed(WebElement componentRenderer) {
        Assert.assertFalse(componentRenderer.isDisplayed());

    }
}
