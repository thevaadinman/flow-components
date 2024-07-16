/*
 * Copyright (C) 2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.textfield.tests;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.textfield.HasAutocorrect;
import org.junit.Assert;
import org.junit.Test;

public class HasAutocorrectTest {

    @Tag("div")
    public static class HasAutocorrectComponent extends Component
            implements HasAutocorrect {

    }

    @Test
    public void defaultValue() {
        HasAutocorrectComponent c = new HasAutocorrectComponent();
        Assert.assertFalse(c.isAutocorrect());
    }

    @Test
    public void enableAutocorrect() {
        HasAutocorrectComponent c = new HasAutocorrectComponent();
        c.setAutocorrect(true);
        Assert.assertTrue(c.isAutocorrect());
    }

    @Test
    public void disableAutocorrect() {
        HasAutocorrectComponent c = new HasAutocorrectComponent();
        c.setAutocorrect(true);
        c.setAutocorrect(false);
    }
}
