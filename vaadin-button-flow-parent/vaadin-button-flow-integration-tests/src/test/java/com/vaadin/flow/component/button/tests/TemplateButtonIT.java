/*
 * Copyright (C) 2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.button.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.tests.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;
import org.openqa.selenium.support.ui.ExpectedConditions;

@TestPath("vaadin-button/template-button")
public class TemplateButtonIT extends AbstractComponentIT {

    @Test
    public void setText_overridesAllContent() {
        open();

        waitUntil(ExpectedConditions
                .presenceOfElementLocated(By.id("button-template")));
        TestBenchElement template = $("*").id("button-template");

        WebElement button = template.$("*").id("button");

        Assert.assertTrue("Button should have displayed", button.isDisplayed());
        Assert.assertEquals("Button should contain caption", "Template caption",
                button.getText());

        button.click();

        button = template.$("*").id("button");

        Assert.assertEquals(
                "Button caption should only be the server side caption",
                "clicked", button.getText());

        WebElement iconButton = template.$("*").id("icon-button");
        Assert.assertTrue("Button should have displayed",
                iconButton.isDisplayed());
        Assert.assertTrue("Button should contain icon.",
                iconButton.findElement(By.tagName("iron-icon")).isDisplayed());
        Assert.assertEquals("Button should contain span with text",
                "Template with icon",
                iconButton.findElement(By.tagName("span")).getText());

        iconButton.click();

        iconButton = template.$("*").id("icon-button");

        Assert.assertEquals("Icon button should only have server side caption",
                "clicked", iconButton.getText());
        Assert.assertTrue("Button should not contain an icon.",
                iconButton.findElements(By.tagName("iron-icon")).isEmpty());
        Assert.assertTrue("Button should not contain a span with text",
                iconButton.findElements(By.tagName("span")).isEmpty());
    }
}
