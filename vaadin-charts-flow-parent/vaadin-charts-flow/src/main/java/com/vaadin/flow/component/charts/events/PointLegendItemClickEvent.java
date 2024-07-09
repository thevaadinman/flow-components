/**
 * Copyright (C) 2000-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.flow.component.charts.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.charts.Chart;

/**
 * The PointLegendItemClickEvent class stores information on click events on the
 * charts's legend items that correspond to a chart point.
 */
@DomEvent("point-legend-item-click")
public class PointLegendItemClickEvent extends ComponentEvent<Chart>
        implements HasItem {

    private final int seriesIndex;
    private final String category;
    private final int pointIndex;
    private final String pointId;

    /**
     * Constructs a SeriesLegendItemClickEvent
     *
     * @param source
     * @param fromClient
     */
    public PointLegendItemClickEvent(Chart source, boolean fromClient,
            @EventData("event.detail.point.series.index") int seriesIndex,
            @EventData("event.detail.point.category") String category,
            @EventData("event.detail.point.index") int pointIndex,
            @EventData("event.detail.point.id") String pointId) {
        super(source, fromClient);
        this.seriesIndex = seriesIndex;
        this.category = category;
        this.pointIndex = pointIndex;
        this.pointId = pointId;
    }

    @Override
    public int getSeriesItemIndex() {
        return seriesIndex;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public int getItemIndex() {
        return pointIndex;
    }

    @Override
    public String getItemId() {
        return pointId;
    }
}
