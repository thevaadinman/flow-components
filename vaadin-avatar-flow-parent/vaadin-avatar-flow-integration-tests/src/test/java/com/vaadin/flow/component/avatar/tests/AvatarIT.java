/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.avatar.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.testutil.TestPath;
import com.vaadin.tests.AbstractComponentIT;

/**
 * Integration tests for the {@link AvatarPage}.
 *
 * @author Vaadin Ltd.
 */
@TestPath("vaadin-avatar/avatar-test")
public class AvatarIT extends AbstractComponentIT {

    private WebElement getPropsBtn;

    @Before
    public void init() {
        open();
        getPropsBtn = findElement(By.id("get-props"));
    }

    @Test
    public void propertiesAreSet() {
        WebElement toggleImg = findElement(By.id("toggle-img"));
        WebElement toggleAbbr = findElement(By.id("toggle-abbr"));
        WebElement toggleName = findElement(By.id("toggle-name"));
        WebElement toggleResource = findElement(By.id("toggle-res"));

        WebElement imgBlock = findElement(By.id("data-block-img"));
        WebElement abbrBlock = findElement(By.id("data-block-abbr"));
        WebElement nameBlock = findElement(By.id("data-block-name"));
        WebElement resourceBlock = findElement(By.id("data-block-resource"));

        toggleImg.click();
        getPropsBtn.click();
        Assert.assertEquals("https://vaadin.com/", imgBlock.getText());

        toggleAbbr.click();
        getPropsBtn.click();
        Assert.assertEquals("BB", abbrBlock.getText());

        toggleName.click();
        getPropsBtn.click();
        Assert.assertEquals("Foo Bar", nameBlock.getText());

        toggleResource.click();
        getPropsBtn.click();
        Assert.assertTrue("img url contains file name",
                resourceBlock.getText().contains("user%2B.png"));

        checkLogsForErrors(); // would fail if the image resource wasn't hosted
    }

    @Test
    public void propertiesAreUnset() {
        WebElement toggleImg = findElement(By.id("toggle-img"));
        WebElement toggleAbbr = findElement(By.id("toggle-abbr"));
        WebElement toggleName = findElement(By.id("toggle-name"));
        WebElement toggleResource = findElement(By.id("toggle-res"));

        WebElement imgBlock = findElement(By.id("data-block-img"));
        WebElement abbrBlock = findElement(By.id("data-block-abbr"));
        WebElement nameBlock = findElement(By.id("data-block-name"));
        WebElement resourceBlock = findElement(By.id("data-block-resource"));

        toggleAbbr.click();
        toggleName.click();
        getPropsBtn.click();

        toggleImg.click();
        toggleImg.click();
        getPropsBtn.click();
        Assert.assertEquals("", imgBlock.getText());

        toggleAbbr.click();
        getPropsBtn.click();
        Assert.assertEquals("", abbrBlock.getText());

        toggleName.click();
        getPropsBtn.click();
        Assert.assertEquals("", nameBlock.getText());

        toggleResource.click();
        toggleResource.click();
        getPropsBtn.click();
        Assert.assertEquals("", resourceBlock.getText());
    }
}
