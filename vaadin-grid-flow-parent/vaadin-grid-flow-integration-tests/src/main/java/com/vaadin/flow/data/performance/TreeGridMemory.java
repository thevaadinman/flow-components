/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.data.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.bean.Address;
import com.vaadin.flow.data.bean.Person;
import com.vaadin.flow.data.provider.hierarchy.TreeData;
import com.vaadin.flow.data.provider.hierarchy.TreeDataProvider;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.Route;

@Route("vaadin-grid/" + TreeGridMemory.PATH)
public class TreeGridMemory extends AbstractBeansMemoryTest<TreeGrid<Person>> {

    public static final String PATH = "tree-grid-memory";

    private boolean initiallyExpanded = false;

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        if (parameter != null && parameter.contains("initiallyExpanded")) {
            initiallyExpanded = true;
        }
        super.setParameter(event, parameter);
    }

    @Override
    protected TreeGrid<Person> createComponent() {
        TreeGrid<Person> treeGrid = new TreeGrid<>();
        treeGrid.addHierarchyColumn(Person::getFirstName)
                .setHeader("First Name");
        treeGrid.addColumn(Person::getLastName).setHeader("Last Name");
        treeGrid.addColumn(person -> Optional.ofNullable(person.getAddress())
                .map(Address::getStreet).orElse(null)).setHeader("Street");
        treeGrid.addColumn(person -> Optional.ofNullable(person.getAddress())
                .map(Address::getPostalCode).map(Object::toString).orElse(""))
                .setHeader("Zip");
        treeGrid.addColumn(person -> Optional.ofNullable(person.getAddress())
                .map(Address::getCity).orElse(null)).setHeader("City");
        return treeGrid;
    }

    @Override
    protected void setInMemoryContainer(TreeGrid<Person> treeGrid,
            List<Person> data) {
        TreeData<Person> treeData = new TreeData<>();
        treeGrid.setDataProvider(new TreeDataProvider<>(treeData));
        List<Person> toExpand = new ArrayList<>();
        if (data.size() != 0 && data.size() % 2 == 0) {
            // treat list as if it were a balanced binary tree
            treeData.addItem(null, data.get(0));
            int index = 0;
            while (2 * index + 2 < data.size()) {
                treeData.addItems(data.get(index),
                        data.subList(2 * index + 1, 2 * index + 3));
                toExpand.add(data.get(index));
                index++;
            }
        } else {
            treeData.addItems(null, data);
        }
        if (initiallyExpanded) {
            treeGrid.expand(toExpand);
        }
    }

    @Override
    protected void setBackendContainer(TreeGrid<Person> component,
            List<Person> data) {
        throw new UnsupportedOperationException();
    }
}
