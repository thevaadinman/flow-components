/*
 * Copyright (C) 2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.textfield.testbench;

import com.vaadin.testbench.HasHelper;
import com.vaadin.testbench.HasLabel;
import com.vaadin.testbench.HasPlaceholder;
import com.vaadin.testbench.HasStringValueProperty;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

/**
 * A TestBench element representing a <code>&lt;vaadin-password-field&gt;</code>
 * element.
 */
@Element("vaadin-password-field")
public class PasswordFieldElement extends TestBenchElement
        implements HasStringValueProperty, HasLabel, HasPlaceholder, HasHelper {

    /**
     * Checks whether the password is shown in clear text or is hidden from
     * view.
     *
     * @return <code>true</code> if the password is shown in clear text,
     *         <code>false</code> if it is hidden from view
     */
    public boolean isPasswordVisible() {
        return getPropertyBoolean("passwordVisible");
    }

    /**
     * Sets whether the password should be shown in clear text or be hidden from
     * view.
     *
     * @param passwordVisible
     *            <code>true</code> to show the password in clear text,
     *            <code>false</code> to hide the password from view
     */
    public void setPasswordVisible(boolean passwordVisible) {
        callFunction("_setPasswordVisible", passwordVisible);
    }

    @Override
    public void setValue(String string) {
        TextFieldElementHelper.setValue(getInputElement(), string);
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        getInputElement().sendKeys(keysToSend);
    }

    private TestBenchElement getInputElement() {
        return (TestBenchElement) getProperty("inputElement");
    }

}
