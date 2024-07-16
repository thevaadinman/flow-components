/*
 * Copyright (C) 2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.grid.it;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.flow.testutil.TestPath;
import com.vaadin.tests.AbstractComponentIT;

@TestPath("vaadin-grid/vaadin-button-inside-grid")
public class ButtonInGridIT extends AbstractComponentIT {

    @Test
    public void pressButtonUsingKeyboard() {
        open();

        new Actions(getDriver()).sendKeys(Keys.TAB, Keys.TAB, Keys.SPACE)
                .build().perform();

        WebElement info = $("div").id("info");
        Assert.assertEquals("foo", info.getText());
    }
}
