/*
 * Copyright (C) 2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.treegrid.it;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.vaadin.flow.component.grid.testbench.TreeGridElement;
import com.vaadin.tests.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;

@TestPath("vaadin-grid/treegrid-empty")
public class TreeGridEmptyIT extends AbstractComponentIT {

    @Test
    public void empty_treegrid_initialized_correctly() {
        open();

        TreeGridElement grid = $(TreeGridElement.class).id("treegrid");

        Assert.assertTrue("Grid should be displayd", grid.isDisplayed());

        Assert.assertTrue("Grid should not have rows", grid.getRowCount() == 0);
    }

    @Test
    public void empty_treegrid_item_expanded() {
        open();

        TreeGridElement grid = $(TreeGridElement.class).id("treegrid");

        findElement(By.id("add-expanded-button")).click();

        Assert.assertEquals("Expected only the parent row to be rendered", 1,
                grid.getRowCount());
    }
}
