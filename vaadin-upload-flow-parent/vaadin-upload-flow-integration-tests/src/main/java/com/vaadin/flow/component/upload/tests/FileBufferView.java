/**
 * Copyright 2000-2024 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See  {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
package com.vaadin.flow.component.upload.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

import org.apache.commons.io.IOUtils;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.vaadin.flow.router.Route;

/**
 * View for {@link Upload} tests using {@link FileBuffer} and
 * {@link MultiFileBuffer}.
 *
 * @author Vaadin Ltd
 */
@Route("vaadin-upload/filebuffer")
public class FileBufferView extends Div {
    public FileBufferView() {
        add(createSingleFileUpload());
        add(createMultiFileUpload());
    }

    private static Div createSingleFileUpload() {
        final FileBuffer buffer = new FileBuffer();
        final Upload singleFileUpload = new Upload(buffer);
        singleFileUpload.setId("single-upload");
        singleFileUpload.setMaxFiles(1);
        return setupUploadSection(singleFileUpload,
                e -> buffer.getInputStream());

    }

    private static Div createMultiFileUpload() {
        final MultiFileBuffer buffer = new MultiFileBuffer();
        final Upload multiFileUpload = new Upload(buffer);
        multiFileUpload.setId("multi-upload");
        return setupUploadSection(multiFileUpload,
                e -> buffer.getInputStream(e.getFileName()));
    }

    private static Div setupUploadSection(Upload upload,
            Function<SucceededEvent, InputStream> streamProvider) {
        final String uploadId = upload.getId().orElse("");
        final Div output = new Div();
        output.setId(uploadId + "-output");
        final Div eventsOutput = new Div();
        eventsOutput.setId(uploadId + "-event-output");

        upload.addSucceededListener(event -> {
            try {
                output.add(event.getFileName());
                output.add(
                        IOUtils.toString(streamProvider.apply(event), "UTF-8"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            eventsOutput.add("-succeeded");
        });

        upload.addAllFinishedListener(event -> eventsOutput.add("-finished"));

        return new Div(output, eventsOutput, upload);
    }

}
