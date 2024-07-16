/*
 * Copyright (C) 2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.splitlayout.testbench;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

/**
 * A TestBench element representing a <code>&lt;vaadin-split-layout&gt;</code>
 * element.
 */
@Element("vaadin-split-layout")
public class SplitLayoutElement extends TestBenchElement {

    public TestBenchElement getSplitter() {
        return getPropertyElement("$", "splitter");
    }
}
