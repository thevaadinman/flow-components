/*
 * Copyright 2000-2022 Vaadin Ltd.
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
package com.vaadin.flow.theme.material;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.theme.AbstractTheme;

/**
 * Material component theme class implementation.
 */
@NpmPackage(value = "@vaadin/vaadin-themable-mixin", version = "24.0.0-alpha7")
@NpmPackage(value = "@vaadin/polymer-legacy-adapter", version = "24.0.0-alpha7")
@JsModule("@vaadin/polymer-legacy-adapter/style-modules.js")
@NpmPackage(value = "@vaadin/vaadin-material-styles", version = "24.0.0-alpha7")
@JsModule("@vaadin/vaadin-material-styles/color.js")
@JsModule("@vaadin/vaadin-material-styles/typography.js")
@JsModule("./material-includes.ts")
public class Material implements AbstractTheme {
    public static final String LIGHT = "light";
    public static final String DARK = "dark";

    @Override
    public String getBaseUrl() {
        return "src/";
    }

    @Override
    public String getThemeUrl() {
        return "theme/material/";
    }

    @Override
    public List<String> getHeaderInlineContents() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, String> getHtmlAttributes(String variant) {
        switch (variant) {
        case LIGHT:
            return Collections.singletonMap("theme", LIGHT);
        case DARK:
            return Collections.singletonMap("theme", DARK);
        default:
            if (!variant.isEmpty()) {
                LoggerFactory.getLogger(getClass().getName()).warn(
                        "Material theme variant not recognized: '{}'. Using no variant.",
                        variant);
            }
            return Collections.emptyMap();
        }
    }
}
