/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.combobox.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.vaadin.flow.component.combobox.testbench.ComboBoxElement;
import com.vaadin.flow.testutil.TestPath;
import com.vaadin.tests.AbstractComponentIT;

@TestPath("vaadin-combo-box/data-provider")
public class DataProviderIT extends AbstractComponentIT {
    @Before
    public void init() {
        open();
        waitUntil(driver -> findElements(By.tagName("vaadin-combo-box"))
                .size() > 0);
    }

    @Test
    public void setValue_ProviderHasGetIdAndValueIdExists_selectionTextShouldBeSet() {
        ComboBoxElement comboBox = $(ComboBoxElement.class)
                .id(DataProviderPage.COMBO_BOX_WITH_GET_ID_ID);

        // The item must have been communicated before it can be found based on
        // its id
        comboBox.openPopup();

        findElement(By.id(DataProviderPage.SET_VALUE_USING_GET_ID_BUTTON_ID))
                .click();

        Assert.assertEquals(
                "ComboBox::setValue must use DataProvider::getId to find the item.",
                "b, Second", comboBox.getSelectedText());
    }

    @Test
    public void setValue_ValueReferenceExists_selectionTextShouldBeSet() {
        findElement(By.id(DataProviderPage.SET_VALUE_USING_REFERENCE_BUTTON_ID))
                .click();
        ComboBoxElement comboBox = $(ComboBoxElement.class)
                .id(DataProviderPage.COMBO_BOX_WITHOUT_GET_ID_ID);
        Assert.assertEquals(
                "ComboBox::setValue must use object reference to find the item.",
                "b, Second", comboBox.getSelectedText());
    }

    @Test
    public void setValue_ValueEqualToAnExistingItem_selectionTextShouldBeSet() {
        ComboBoxElement comboBox = $(ComboBoxElement.class)
                .id(DataProviderPage.COMBO_BOX_WITHOUT_GET_ID_ID);

        // The item must have been communicated before it can be found based on
        // equals
        comboBox.openPopup();

        findElement(By.id(DataProviderPage.SET_VALUE_USING_EQUALS_BUTTON_ID))
                .click();
        Assert.assertEquals(
                "ComboBox::setValue must use Object::equals to find the item.",
                "c, Third", comboBox.getSelectedText());
    }

    @Test
    public void loadData_refreshAllWithSmallerDataSet_correctItemsDisplayed() {
        ComboBoxElement comboBox = $(ComboBoxElement.class)
                .id("combo-box-with-reduce-data-set");
        comboBox.openPopup();
        comboBox.closePopup();

        findElement(By.id("refresh-all-with-smaller-data-set")).click();
        comboBox.openPopup();

        waitUntil(e -> "bar".equals(comboBox.getOptions().get(0)));

        List<String> items = comboBox.getOptions();
        Assert.assertEquals(
                "Expected one item to be visible after refreshing "
                        + "the data provider with a smaller data set.",
                1, items.size());
        Assert.assertEquals(
                "Item is not the same as in the refreshed data provider.",
                "bar", items.get(0));
    }
}
