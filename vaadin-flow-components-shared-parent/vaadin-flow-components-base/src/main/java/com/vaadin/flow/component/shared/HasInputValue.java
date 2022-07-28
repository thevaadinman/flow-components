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
package com.vaadin.flow.component.shared;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.Synchronize;

/**
 * Mixin interface for components that has an internal input element. Intended
 * for internal use.
 *
 * @author Vaadin Ltd
 */
public interface HasInputValue extends HasElement {

    /**
     * Gets the populated state of the input's value, which is {@code false} by
     * default.
     *
     * @return <code>true</code> if the input's value is populated,
     *         <code>false</code> otherwise
     */
    @Synchronize(property = "__inputValuePopulated", value = "input-value-populated-changed")
    default boolean isInputValuePopulated() {
        return getElement().getProperty("__inputValuePopulated", false);
    }
}
