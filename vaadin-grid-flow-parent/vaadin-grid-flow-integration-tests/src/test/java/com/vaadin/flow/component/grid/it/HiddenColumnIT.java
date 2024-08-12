/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.grid.it;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.component.grid.testbench.GridElement;
import com.vaadin.flow.testutil.TestPath;
import com.vaadin.tests.AbstractComponentIT;

@TestPath("vaadin-grid/hidden-column")
public class HiddenColumnIT extends AbstractComponentIT {

    private GridElement grid;

    @Before
    public void setUp() {
        open();
        grid = $(GridElement.class).first();
        waitUntil(driver -> grid.getRowCount() > 0);
    }

    @Test
    public void hideColumn_replaceItems_unhideColumn_dataIsRendered() {
        WebElement hideButton = findElement(By.id("hide-unhide"));
        hideButton.click();

        Assert.assertEquals("bar@gmail.com", grid.getCell(0, 0).getText());

        findElement(By.id("update")).click();

        hideButton.click();

        Assert.assertEquals("bar", grid.getCell(0, 0).getText());
    }

    @Test
    @Ignore
    public void hideColumnWithEditor_replaceItems_unhideColumn_dataIsRendered() {
        // open an editor
        grid.getRow(0).doubleClick();

        Assert.assertEquals("Editor is not rendered after double click", 1,
                grid.getCell(0, 0).$("vaadin-text-field").all().size());

        WebElement hideButton = findElement(By.id("hide-unhide"));
        hideButton.click();

        Assert.assertEquals("bar@gmail.com", grid.getCell(0, 0).getText());

        // unhide
        hideButton.click();

        // the editor comes back
        Assert.assertEquals("Editor is not rendered after double click", 1,
                grid.getCell(0, 0).$("vaadin-text-field").all().size());

        hideButton.click();

        // close the editor which is hidden
        grid.sendKeys(Keys.ESCAPE);

        Assert.assertEquals("bar@gmail.com", grid.getCell(0, 0).getText());

        // unhide
        hideButton.click();

        // the editor is closed and the first cell is the name cell
        Assert.assertEquals("foo", grid.getCell(0, 0).getText());
    }
}
