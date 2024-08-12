/*
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.datetimepicker.validation;

import static com.vaadin.flow.component.datetimepicker.validation.DateTimePickerValidationBinderPage.CLEAR_VALUE_BUTTON;
import static com.vaadin.flow.component.datetimepicker.validation.DateTimePickerValidationBinderPage.EXPECTED_VALUE_INPUT;
import static com.vaadin.flow.component.datetimepicker.validation.DateTimePickerValidationBinderPage.MAX_INPUT;
import static com.vaadin.flow.component.datetimepicker.validation.DateTimePickerValidationBinderPage.MIN_INPUT;
import static com.vaadin.flow.component.datetimepicker.validation.DateTimePickerValidationBinderPage.REQUIRED_ERROR_MESSAGE;
import static com.vaadin.flow.component.datetimepicker.validation.DateTimePickerValidationBinderPage.UNEXPECTED_VALUE_ERROR_MESSAGE;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;

import com.vaadin.flow.component.datetimepicker.testbench.DateTimePickerElement;
import com.vaadin.flow.testutil.TestPath;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.tests.validation.AbstractValidationIT;

@TestPath("vaadin-date-time-picker/validation/binder")
public class DateTimePickerValidationBinderIT
        extends AbstractValidationIT<DateTimePickerElement> {
    private TestBenchElement dateInput;
    private TestBenchElement timeInput;

    @Before
    public void init() {
        super.init();
        dateInput = testField.$("input").first();
        timeInput = testField.$("input").last();
    }

    @Test
    public void fieldIsInitiallyValid() {
        assertClientValid();
        assertServerValid();
        assertErrorMessage(null);
    }

    @Test
    public void required_triggerDateInputBlur_assertValidity() {
        dateInput.sendKeys(Keys.TAB);
        timeInput.sendKeys(Keys.TAB);
        assertServerInvalid();
        assertClientInvalid();
        assertErrorMessage(REQUIRED_ERROR_MESSAGE);
    }

    @Test
    public void required_triggerTimeInputBlur_assertValidity() {
        timeInput.sendKeys(Keys.TAB);
        assertServerInvalid();
        assertClientInvalid();
        assertErrorMessage(REQUIRED_ERROR_MESSAGE);
    }

    @Test
    public void required_changeInputValue_assertValidity() {
        $("input").id(EXPECTED_VALUE_INPUT).sendKeys("2000-01-01T12:00",
                Keys.ENTER);

        setInputValue(dateInput, "1/1/2000");
        setInputValue(timeInput, "12:00");
        assertServerValid();
        assertClientValid();

        setInputValue(dateInput, "");
        assertServerInvalid();
        assertClientInvalid();
        assertErrorMessage(REQUIRED_ERROR_MESSAGE);

        setInputValue(timeInput, "");
        assertServerInvalid();
        assertClientInvalid();
        assertErrorMessage(REQUIRED_ERROR_MESSAGE);
    }

    @Test
    public void min_changeInputValue_assertValidity() {
        $("input").id(MIN_INPUT).sendKeys("2000-02-02T12:00", Keys.ENTER);
        $("input").id(EXPECTED_VALUE_INPUT).sendKeys("2000-03-03T11:00",
                Keys.ENTER);

        setInputValue(dateInput, "1/1/2000");
        setInputValue(timeInput, "11:00");
        assertClientInvalid();
        assertServerInvalid();
        assertErrorMessage("");

        setInputValue(dateInput, "2/2/2000");
        setInputValue(timeInput, "11:00");
        assertClientInvalid();
        assertServerInvalid();
        assertErrorMessage("");

        setInputValue(dateInput, "2/2/2000");
        setInputValue(timeInput, "12:00");
        assertClientInvalid();
        assertServerInvalid();
        assertErrorMessage(UNEXPECTED_VALUE_ERROR_MESSAGE);

        setInputValue(dateInput, "2/2/2000");
        setInputValue(timeInput, "13:00");
        assertClientInvalid();
        assertServerInvalid();
        assertErrorMessage(UNEXPECTED_VALUE_ERROR_MESSAGE);

        setInputValue(dateInput, "3/3/2000");
        setInputValue(timeInput, "11:00");
        assertClientValid();
        assertServerValid();
    }

    @Test
    public void max_changeInputValue_assertValidity() {
        $("input").id(MAX_INPUT).sendKeys("2000-02-02T12:00", Keys.ENTER);
        $("input").id(EXPECTED_VALUE_INPUT).sendKeys("2000-01-01T13:00",
                Keys.ENTER);

        setInputValue(dateInput, "3/3/2000");
        setInputValue(timeInput, "13:00");
        assertClientInvalid();
        assertServerInvalid();
        assertErrorMessage("");

        setInputValue(dateInput, "2/2/2000");
        setInputValue(timeInput, "13:00");
        assertClientInvalid();
        assertServerInvalid();
        assertErrorMessage("");

        setInputValue(dateInput, "2/2/2000");
        setInputValue(timeInput, "12:00");
        assertClientInvalid();
        assertServerInvalid();
        assertErrorMessage(UNEXPECTED_VALUE_ERROR_MESSAGE);

        setInputValue(dateInput, "2/2/2000");
        setInputValue(timeInput, "11:00");
        assertClientInvalid();
        assertServerInvalid();
        assertErrorMessage(UNEXPECTED_VALUE_ERROR_MESSAGE);

        setInputValue(dateInput, "1/1/2000");
        setInputValue(timeInput, "13:00");
        assertClientValid();
        assertServerValid();
    }

    @Test
    public void badInput_changeValue_assertValidity() {
        $("input").id(EXPECTED_VALUE_INPUT).sendKeys("2000-01-01T10:00",
                Keys.ENTER);

        setInputValue(dateInput, "INVALID");
        setInputValue(timeInput, "INVALID");
        assertServerInvalid();
        assertClientInvalid();
        assertErrorMessage("");

        setInputValue(dateInput, "1/1/2000");
        setInputValue(timeInput, "10:00");
        assertServerValid();
        assertClientValid();

        setInputValue(dateInput, "INVALID");
        setInputValue(timeInput, "INVALID");
        assertServerInvalid();
        assertClientInvalid();
        assertErrorMessage("");
    }

    @Test
    public void badInput_setValue_clearValue_assertValidity() {
        setInputValue(dateInput, "INVALID");
        setInputValue(timeInput, "INVALID");
        assertServerInvalid();
        assertClientInvalid();
        assertErrorMessage("");

        $("button").id(CLEAR_VALUE_BUTTON).click();
        assertServerInvalid();
        assertClientInvalid();
        assertErrorMessage(REQUIRED_ERROR_MESSAGE);
    }

    protected DateTimePickerElement getTestField() {
        return $(DateTimePickerElement.class).first();
    }

    private void setInputValue(TestBenchElement input, String value) {
        input.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        input.sendKeys(value, Keys.ENTER);
    }
}
