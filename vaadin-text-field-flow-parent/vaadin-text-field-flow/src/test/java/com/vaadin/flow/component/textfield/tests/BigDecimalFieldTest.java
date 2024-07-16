/*
 * Copyright (C) 2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.textfield.tests;

import java.math.BigDecimal;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.textfield.BigDecimalField;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BigDecimalFieldTest extends TextFieldTest {

    private BigDecimalField field;

    @Before
    public void init() {
        field = new BigDecimalField();
        field.setLocale(Locale.US);
    }

    @Override
    @Test
    public void setValueNull() {
        assertNull("Value should be null", field.getValue());
        field.setValue(new BigDecimal("1"));
        field.setValue(null); // not throwing
    }

    @Override
    @Test
    public void initialValuePropertyValue() {
        assertEquals(field.getEmptyValue(),
                field.getElement().getProperty("value"));
    }

    @Test
    public void valueFormatting_scientificNotationRemoved() {
        assertValueFormatting(new BigDecimal("1e9"), "1000000000");
        assertValueFormatting(new BigDecimal("-1e9"), "-1000000000");

        assertValueFormatting(new BigDecimal("1e-9"), "0.000000001");
        assertValueFormatting(new BigDecimal("-1e-9"), "-0.000000001");
    }

    @Test
    public void valueFormatting_trailingZerosPreserved_leadingZerosRemoved() {
        assertValueFormatting(new BigDecimal("001.100"), "1.100");
    }

    @Test
    public void valueFormatting_scalePreserved() {
        assertValueFormatting(new BigDecimal("1.10").setScale(4), "1.1000");
        assertValueFormatting(new BigDecimal("1.10").setScale(1), "1.1");
    }

    @Test
    public void valueFormatting_scaleWithRounding() {
        assertValueFormatting(
                new BigDecimal("1.01").setScale(1, BigDecimal.ROUND_CEILING),
                "1.1");
        assertValueFormatting(
                new BigDecimal("1.01").setScale(1, BigDecimal.ROUND_FLOOR),
                "1.0");
    }

    private void assertValueFormatting(BigDecimal bigDecimal,
            String expectedValueProp) {
        field.setValue(bigDecimal);
        Assert.assertEquals(expectedValueProp,
                field.getElement().getProperty("value"));
    }

}
