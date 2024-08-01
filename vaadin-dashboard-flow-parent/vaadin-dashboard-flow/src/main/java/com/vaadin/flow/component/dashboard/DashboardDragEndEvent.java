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
package com.vaadin.flow.component.dashboard;

import java.util.List;

import com.vaadin.flow.component.ComponentEvent;

/**
 * Drag end event of {@link Grid} rows.
 *
 * @param <T>
 *            The Grid bean type.
 * @author Vaadin Ltd.
 * @see Grid#addDragEndListener(GridDragEndListener)
 */
@SuppressWarnings("serial")
public class DashboardDragEndEvent<T extends DashboardWidget>
        extends ComponentEvent<Dashboard<T>> {

    private final transient List<T> widgets;

    public DashboardDragEndEvent(Dashboard<T> source, List<T> widgets) {
        super(source, true);
        this.widgets = widgets;
    }

    public List<T> getWidgets() {
        return widgets;
    }

}
