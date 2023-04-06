/**
 * Copyright (C) 2000-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.flow.component.upload;

/**
 * FailedEvent that indicates that an input stream could not be obtained.
 */
public class NoInputStreamEvent extends FailedEvent {

    /**
     * Create an instance of the event.
     *
     * @param source
     *            the source of the file
     * @param fileName
     *            the received file name
     * @param mimeType
     *            the MIME type of the received file
     * @param length
     *            the length of the received file
     */
    public NoInputStreamEvent(Upload source, String fileName, String mimeType,
            long length) {
        super(source, fileName, mimeType, length);
    }

}
