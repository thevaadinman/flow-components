/**
 * Copyright (C) 2000-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.flow.component.tabs;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * <p>
 * Description copied from corresponding location in WebComponent:
 * </p>
 * <p>
 * {@code <vaadin-tab>} is a Web Component providing an accessible and
 * customizable tab.
 * </p>
 * <p>
 * {@code
<vaadin-tab>
Tab 1
</vaadin-tab>}
 * </p>
 * <p>
 * The following state attributes are available for styling:
 * </p>
 * <table>
 * <thead>
 * <tr>
 * <th>Attribute</th>
 * <th>Description</th>
 * <th>Part name</th>
 * </tr>
 * </thead> <tbody>
 * <tr>
 * <td>{@code disabled}</td>
 * <td>Set to a disabled tab</td>
 * <td>:host</td>
 * </tr>
 * <tr>
 * <td>{@code focused}</td>
 * <td>Set when the element is focused</td>
 * <td>:host</td>
 * </tr>
 * <tr>
 * <td>{@code focus-ring}</td>
 * <td>Set when the element is keyboard focused</td>
 * <td>:host</td>
 * </tr>
 * <tr>
 * <td>{@code selected}</td>
 * <td>Set when the tab is selected</td>
 * <td>:host</td>
 * </tr>
 * <tr>
 * <td>{@code active}</td>
 * <td>Set when mousedown or enter/spacebar pressed</td>
 * <td>:host</td>
 * </tr>
 * <tr>
 * <td>{@code orientation}</td>
 * <td>Set to {@code horizontal} or {@code vertical} depending on the direction
 * of items</td>
 * <td>:host</td>
 * </tr>
 * </tbody>
 * </table>
 * <p>
 * See
 * <a href="https://github.com/vaadin/vaadin-themable-mixin/wiki">ThemableMixin
 * – how to apply styles for shadow parts</a>
 * </p>
 */
@Tag("vaadin-tab")
@HtmlImport("frontend://bower_components/vaadin-tabs/src/vaadin-tab.html")
@JsModule("@vaadin/vaadin-tabs/src/vaadin-tab.js")
@NpmPackage(value = "@vaadin/vaadin-tabs", version = "3.2.0")
public abstract class GeneratedVaadinTab<R extends GeneratedVaadinTab<R>>
        extends Component implements HasStyle, HasTheme {

    /**
     * Adds theme variants to the component.
     *
     * @param variants
     *            theme variants to add
     */
    public void addThemeVariants(TabVariant... variants) {
        getThemeNames().addAll(Stream.of(variants)
                .map(TabVariant::getVariantName).collect(Collectors.toList()));
    }

    /**
     * Removes theme variants from the component.
     *
     * @param variants
     *            theme variants to remove
     */
    public void removeThemeVariants(TabVariant... variants) {
        getThemeNames().removeAll(Stream.of(variants)
                .map(TabVariant::getVariantName).collect(Collectors.toList()));
    }

    /**
     * <p>
     * Description copied from corresponding location in WebComponent:
     * </p>
     * <p>
     * Submittable string value. The default value is the trimmed text content
     * of the element.
     * <p>
     * This property is not synchronized automatically from the client side, so
     * the returned value may not be the same as in client side.
     * </p>
     *
     * @return the {@code value} property from the webcomponent
     */
    protected String getValueString() {
        return getElement().getProperty("value");
    }

    /**
     * <p>
     * Description copied from corresponding location in WebComponent:
     * </p>
     * <p>
     * Submittable string value. The default value is the trimmed text content
     * of the element.
     * </p>
     *
     * @param value
     *            the String value to set
     */
    protected void setValue(String value) {
        getElement().setProperty("value", value == null ? "" : value);
    }

    /**
     * <p>
     * Description copied from corresponding location in WebComponent:
     * </p>
     * <p>
     * If true, the user cannot interact with this element.
     * <p>
     * This property is not synchronized automatically from the client side, so
     * the returned value may not be the same as in client side.
     * </p>
     *
     * @return the {@code disabled} property from the webcomponent
     */
    protected boolean isDisabledBoolean() {
        return getElement().getProperty("disabled", false);
    }

    /**
     * <p>
     * Description copied from corresponding location in WebComponent:
     * </p>
     * <p>
     * If true, the user cannot interact with this element.
     * </p>
     *
     * @param disabled
     *            the boolean value to set
     */
    protected void setDisabled(boolean disabled) {
        getElement().setProperty("disabled", disabled);
    }

    /**
     * <p>
     * Description copied from corresponding location in WebComponent:
     * </p>
     * <p>
     * If true, the item is in selected state.
     * <p>
     * This property is not synchronized automatically from the client side, so
     * the returned value may not be the same as in client side.
     * </p>
     *
     * @return the {@code selected} property from the webcomponent
     */
    protected boolean isSelectedBoolean() {
        return getElement().getProperty("selected", false);
    }

    /**
     * <p>
     * Description copied from corresponding location in WebComponent:
     * </p>
     * <p>
     * If true, the item is in selected state.
     * </p>
     *
     * @param selected
     *            the boolean value to set
     */
    protected void setSelected(boolean selected) {
        getElement().setProperty("selected", selected);
    }
}
