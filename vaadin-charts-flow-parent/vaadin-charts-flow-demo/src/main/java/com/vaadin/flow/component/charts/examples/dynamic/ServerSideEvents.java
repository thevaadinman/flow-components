/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.charts.examples.dynamic;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.AbstractChartExample;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.SkipFromDemo;
import com.vaadin.flow.component.charts.model.AbstractSeries;
import com.vaadin.flow.component.charts.model.AxisTitle;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.charts.model.Dimension;
import com.vaadin.flow.component.charts.model.PlotLine;
import com.vaadin.flow.component.charts.model.PlotOptionsSeries;
import com.vaadin.flow.component.charts.model.Series;
import com.vaadin.flow.component.charts.model.Tooltip;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.server.Command;

@SkipFromDemo
public class ServerSideEvents extends AbstractChartExample {

    private Chart chart;
    private int id;
    private OrderedList historyLayout;
    private Checkbox visibilityToggling;
    private boolean setExtremes = true;

    @Override
    public void initDemo() {
        historyLayout = new OrderedList();
        historyLayout.setId("history");

        chart = new Chart();
        chart.setId("chart");

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setWidth(500);
        configuration.getChart().setType(ChartType.SCATTER);
        configuration.getTitle().setText("Test server side events.");
        configuration.getSubTitle().setText(
                "When an event occurs, the details are shown below the chart");
        configuration.setExporting(true);
        configuration.getChart().setAnimation(false);
        configuration.getChart().setZoomType(Dimension.XY);

        XAxis xAxis = configuration.getxAxis();
        xAxis.setMinPadding(0.2);
        xAxis.setMaxPadding(0.2);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle("Value"));
        PlotLine plotline = new PlotLine();
        plotline.setValue(0);
        yAxis.setPlotLines(plotline);
        yAxis.setMinPadding(0.2);
        yAxis.setMaxPadding(0.2);

        YAxis yAxis1 = new YAxis();
        yAxis1.setTitle("Another axis");
        yAxis1.setOpposite(true);
        configuration.addyAxis(yAxis1);

        PlotOptionsSeries opt = new PlotOptionsSeries();
        opt.setShowCheckbox(true);
        opt.setAllowPointSelect(true);

        configuration.setPlotOptions(opt);
        configuration.setTooltip(new Tooltip(false));
        final DataSeries series1 = createDataSeries(0);
        final DataSeries series2 = createDataSeries(20);
        DataSeries series3 = createDataSeries(100);
        series3.get(0).setY(105);
        series3.get(3).setY(95);
        series3.setName("Another axis");
        series3.setyAxis(1);
        DataSeriesItem firstDataPoint = series1.get(0);
        firstDataPoint.setSelected(true);
        configuration.setSeries(series1, series2, series3);

        chart.addChartClickListener(this::logEvent);
        chart.addPointClickListener(this::logEvent);
        chart.addCheckBoxClickListener(this::logEvent);
        chart.addSeriesLegendItemClickListener(this::logEvent);
        chart.addSeriesHideListener(this::logEvent);
        chart.addSeriesShowListener(this::logEvent);
        chart.addPointSelectListener(this::logEvent);
        chart.addPointUnselectListener(this::logEvent);
        chart.addYAxesExtremesSetListener(this::logEvent);
        chart.drawChart();

        chart.setVisibilityTogglingDisabled(false);

        VerticalLayout layout = new VerticalLayout();
        layout.setId("master");
        layout.add(createControls());

        layout.add(new H2("Event History"), historyLayout);
        add(chart, layout);
    }

    private Component createControls() {
        visibilityToggling = new Checkbox("Disable series visibility toggling");
        visibilityToggling.setId("visibilityToggler");
        visibilityToggling.addValueChangeListener(e -> chart
                .setVisibilityTogglingDisabled(visibilityToggling.getValue()));
        visibilityToggling.setValue(false);

        final Button firstSeriesVisible = new Button("Hide first series");
        firstSeriesVisible.setId("hideFirstSeries");
        firstSeriesVisible.addClickListener(
                new ComponentEventListener<ClickEvent<Button>>() {
                    private boolean hideSeries = true;

                    @Override
                    public void onComponentEvent(
                            ClickEvent<Button> buttonClickEvent) {
                        Series firstSeries = chart.getConfiguration()
                                .getSeries().get(0);
                        ((AbstractSeries) firstSeries).setVisible(!hideSeries);
                        hideSeries = !hideSeries;
                    }
                });

        final RadioButtonGroup<Dimension> zoomLevels = new RadioButtonGroup<>();
        zoomLevels.setItems(Dimension.XY, Dimension.X, Dimension.Y);
        zoomLevels.setValue(Dimension.XY);
        zoomLevels.addValueChangeListener(event -> {
            chart.getConfiguration().getChart()
                    .setZoomType(zoomLevels.getValue());
            chart.drawChart();
        });

        Button resetHistory = new Button("Reset history");
        resetHistory.setId("resetHistory");
        resetHistory.addClickListener(event -> {
            historyLayout.removeAll();
        });

        Button toggleExtremes = new Button("Toggle Extremes");
        toggleExtremes.setId("toggleExtremes");
        toggleExtremes.addClickListener(e -> {
            if (setExtremes) {
                chart.getConfiguration().getyAxes().getAxis(0).setExtremes(9,
                        15);
            } else {
                chart.getConfiguration().resetZoom();
            }
            setExtremes = !setExtremes;
        });

        HorizontalLayout controls = new HorizontalLayout();
        controls.setId("controls");
        controls.add(visibilityToggling);
        controls.add(firstSeriesVisible);
        controls.add(zoomLevels);
        controls.add(resetHistory);
        controls.add(toggleExtremes);
        return controls;
    }

    private DataSeries createDataSeries(Number yvalue) {
        final DataSeries series = new DataSeries();
        series.add(new DataSeriesItem(20, yvalue));
        series.add(new DataSeriesItem(40, yvalue.intValue() + 10));
        series.add(new DataSeriesItem(60, yvalue.intValue() - 15));
        series.add(new DataSeriesItem(80, yvalue));
        series.setId("" + id);
        series.setName("Test Series " + id++);
        return series;
    }

    private void logEvent(ComponentEvent<Chart> event) {
        String name = event.getClass().getSimpleName();
        String details = createEventString(event);
        Span eventTypeSpan = new Span(name);
        eventTypeSpan.setId("event-type");
        Span eventDetailsSpan = new Span(details);
        eventDetailsSpan.setId("event-details");
        ListItem listItem = new ListItem(eventTypeSpan, eventDetailsSpan);
        historyLayout.add(listItem);
    }

    private String createEventString(ComponentEvent<Chart> event) {
        ObjectMapper mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setVisibility(PropertyAccessor.ALL,
                        JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD,
                        JsonAutoDetect.Visibility.ANY)
                .addMixIn(Command.class, JacksonMixinForIgnoreCommand.class);

        try {
            return mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @JsonIgnoreType
    static class JacksonMixinForIgnoreCommand {
    }
}
