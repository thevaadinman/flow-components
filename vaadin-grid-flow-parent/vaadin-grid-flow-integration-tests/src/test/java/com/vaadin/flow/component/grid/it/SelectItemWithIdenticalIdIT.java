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
import org.junit.Test;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.checkbox.testbench.CheckboxElement;
import com.vaadin.flow.component.grid.testbench.GridElement;
import com.vaadin.flow.testutil.TestPath;
import com.vaadin.tests.AbstractComponentIT;

@TestPath("vaadin-grid/select-item-with-identical-id")
public class SelectItemWithIdenticalIdIT extends AbstractComponentIT {

    private ButtonElement addGridWithPreselectionButton;

    private ButtonElement selectItem2Button;

    private ButtonElement deselectItem1Button;

    private CheckboxElement useMultiSelectCheckbox;

    @Before
    public void init() {
        open();
        addGridWithPreselectionButton = $(ButtonElement.class)
                .id("add-grid-with-preselection-button");
        selectItem2Button = $(ButtonElement.class).id("select-item-2-button");
        deselectItem1Button = $(ButtonElement.class)
                .id("deselect-item-1-button");
        useMultiSelectCheckbox = $(CheckboxElement.class).first();
    }

    @Test
    public void addGridWithSelection_itemIsNotUpdated() {
        addGridWithPreselectionButton.click();
        GridElement grid = $(GridElement.class).waitForFirst();

        Assert.assertEquals("1", grid.getCell(0, 0).getText());
    }

    @Test
    public void addGridWithSelection_deselectItem_itemIsNotUpdated() {
        addGridWithPreselectionButton.click();
        GridElement grid = $(GridElement.class).waitForFirst();

        deselectItem1Button.click();

        Assert.assertEquals("1", grid.getCell(0, 0).getText());
    }

    @Test
    public void addGridWithSelection_selectAnotherItem_itemIsNotUpdated() {
        addGridWithPreselectionButton.click();
        GridElement grid = $(GridElement.class).waitForFirst();

        selectItem2Button.click();

        Assert.assertEquals("2", grid.getCell(1, 0).getText());
    }

    @Test
    public void setMultiSelect_addGridWithSelection_itemIsNotUpdated() {
        useMultiSelectCheckbox.setChecked(true);
        addGridWithPreselectionButton.click();
        GridElement grid = $(GridElement.class).waitForFirst();

        Assert.assertEquals("1", grid.getCell(0, 1).getText());
    }

    @Test
    public void setMultiSelect_addGridWithSelection_deselectItem_itemIsNotUpdated() {
        useMultiSelectCheckbox.setChecked(true);
        addGridWithPreselectionButton.click();
        GridElement grid = $(GridElement.class).waitForFirst();

        deselectItem1Button.click();

        Assert.assertEquals("1", grid.getCell(0, 1).getText());
    }

    @Test
    public void setMultiSelect_addGridWithSelection_selectAnotherItem_itemIsNotUpdated() {
        useMultiSelectCheckbox.setChecked(true);
        addGridWithPreselectionButton.click();
        GridElement grid = $(GridElement.class).waitForFirst();

        selectItem2Button.click();

        Assert.assertEquals("2", grid.getCell(1, 1).getText());
    }
}
