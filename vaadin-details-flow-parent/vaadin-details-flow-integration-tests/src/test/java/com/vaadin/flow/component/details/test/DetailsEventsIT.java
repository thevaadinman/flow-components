/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.details.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.details.testbench.DetailsElement;
import com.vaadin.flow.testutil.TestPath;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.tests.AbstractComponentIT;

@TestPath("vaadin-details/events")
public class DetailsEventsIT extends AbstractComponentIT {
    private DetailsElement details;
    private TestBenchElement toggle;
    private TestBenchElement output;

    @Before
    public void init() {
        open();
        details = $(DetailsElement.class).waitForFirst();
        toggle = $(TestBenchElement.class).id("toggle");
        output = $(TestBenchElement.class).id("output");
    }

    @Test
    public void noInitialOpenedChangeEvent() {
        Assert.assertEquals("", output.getText());
    }

    @Test
    public void toggleOnClient_openedChangeEventIsFromClient() {
        details.toggle();
        Assert.assertEquals("Opened changed: opened=true, isFromClient=true",
                output.getText());
    }

    @Test
    public void toggleOnServer_openedChangeEventIsNotFromClient() {
        toggle.click();
        Assert.assertEquals("Opened changed: opened=true, isFromClient=false",
                output.getText());
    }
}
