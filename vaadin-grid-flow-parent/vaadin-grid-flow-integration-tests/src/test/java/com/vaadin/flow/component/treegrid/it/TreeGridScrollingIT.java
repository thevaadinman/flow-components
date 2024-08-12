/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.treegrid.it;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.testutil.TestPath;

@TestPath("vaadin-grid/treegrid-scrolling")
public class TreeGridScrollingIT extends AbstractTreeGridIT {

    @Before
    public void before() {
        open();
        setupTreeGrid();
    }

    @Test
    public void scrollingTree_expandCollapseFromBeginning_correctItemsShown() {
        Assert.assertEquals(getTreeGrid().getRowCount(),
                TreeGridScrollingPage.DEFAULT_NODES);

        verifyRow(0, 0, 0);
        verifyRow(10, 0, 10);
        verifyRow(19, 0, 19);
        verifyRow(10, 0, 10);
        verifyRow(0, 0, 0);

        getTreeGrid().expandWithClick(0);

        verifyRow(0, 0, 0);
        verifyRow(1, 1, 0);
        verifyRow(11, 1, 10);
        verifyRow(20, 1, 19);
        verifyRow(39, 0, 19);

        // verifying in reverse order causes scrolling up
        verifyRow(20, 1, 19);
        verifyRow(11, 1, 10);
        verifyRow(1, 1, 0);
        verifyRow(0, 0, 0);

        getTreeGrid().expandWithClick(3);

        verifyRow(0, 0, 0);

        verifyRow(1, 1, 0);
        verifyRow(2, 1, 1);
        verifyRow(3, 1, 2);

        verifyRow(4, 2, 0);

        verifyRow(14, 2, 10);
        verifyRow(23, 2, 19);
        verifyRow(24, 1, 3);
        verifyRow(40, 1, 19);
        verifyRow(59, 0, 19);

        // scroll back up

        verifyRow(40, 1, 19);
        verifyRow(24, 1, 3);
        verifyRow(23, 2, 19);
        verifyRow(14, 2, 10);

        verifyRow(4, 2, 0);
        verifyRow(2, 1, 1);
        verifyRow(3, 1, 2);
        verifyRow(1, 1, 0);
        verifyRow(0, 0, 0);

        getTreeGrid().expandWithClick(2);

        verifyRow(0, 0, 0);

        verifyRow(1, 1, 0);
        verifyRow(2, 1, 1);
        verifyRow(3, 2, 0);
        verifyRow(22, 2, 19);

        verifyRow(23, 1, 2);
        verifyRow(24, 2, 0);

        verifyRow(43, 2, 19);
        verifyRow(44, 1, 3);
        verifyRow(60, 1, 19);
        verifyRow(79, 0, 19);

        // scroll back up
        verifyRow(60, 1, 19);
        verifyRow(44, 1, 3);
        verifyRow(43, 2, 19);

        verifyRow(24, 2, 0);
        verifyRow(23, 1, 2);

        verifyRow(22, 2, 19);
        verifyRow(3, 2, 0);
        verifyRow(2, 1, 1);
        verifyRow(1, 1, 0);

        verifyRow(0, 0, 0);

        getTreeGrid().collapseWithClick(2);

        verifyRow(0, 0, 0);

        verifyRow(1, 1, 0);
        verifyRow(2, 1, 1);
        verifyRow(3, 1, 2);

        verifyRow(4, 2, 0);

        verifyRow(14, 2, 10);
        verifyRow(23, 2, 19);
        verifyRow(24, 1, 3);
        verifyRow(40, 1, 19);
        verifyRow(59, 0, 19);

        // scroll back up

        verifyRow(40, 1, 19);
        verifyRow(24, 1, 3);
        verifyRow(23, 2, 19);
        verifyRow(14, 2, 10);

        verifyRow(4, 2, 0);
        verifyRow(2, 1, 1);
        verifyRow(3, 1, 2);
        verifyRow(1, 1, 0);
        verifyRow(0, 0, 0);

        getTreeGrid().collapseWithClick(3);

        verifyRow(0, 0, 0);
        verifyRow(1, 1, 0);
        verifyRow(11, 1, 10);
        verifyRow(20, 1, 19);
        verifyRow(39, 0, 19);

        // scroll back up

        verifyRow(20, 1, 19);
        verifyRow(11, 1, 10);
        verifyRow(1, 1, 0);
        verifyRow(0, 0, 0);

        getTreeGrid().collapseWithClick(0);

        verifyRow(0, 0, 0);
        verifyRow(10, 0, 10);
        verifyRow(19, 0, 19);
        verifyRow(10, 0, 10);
        verifyRow(0, 0, 0);
    }

    private void verifyRow(int rowActualIndex, int depth, int levelIndex) {
        Assert.assertEquals("Invalid row at index " + rowActualIndex,
                depth + " | " + levelIndex,
                getTreeGrid().getCell(rowActualIndex, 0).getText());
    }
}
