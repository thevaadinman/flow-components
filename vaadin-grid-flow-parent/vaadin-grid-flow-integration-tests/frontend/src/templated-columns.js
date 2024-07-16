/*
 * Copyright (C) 2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See {@literal <https://vaadin.com/commercial-license-and-service-terms>}  for the full
 * license.
 */
import { PolymerElement } from '@polymer/polymer/polymer-element.js';

import { html } from '@polymer/polymer/lib/utils/html-tag.js';
class TemplatedColumns extends PolymerElement {
  static get template() {
    return html`
        <vaadin-grid id="grid">

            <vaadin-grid-column width="30px" flex-grow="0">
                <template class="header">#</template>
                <template>[[index]]</template>
            </vaadin-grid-column>

            <vaadin-grid-column-group>
                <template class="header">Name</template>

                <vaadin-grid-column>
                  <template class="header">First</template>
                  <template>[[item.name.first]]</template>
                </vaadin-grid-column>

                <vaadin-grid-column>
                  <template class="header">Last</template>
                  <template>[[item.name.last]]</template>
                </vaadin-grid-column>
            </vaadin-grid-column-group>

            <vaadin-grid-column-group>
                <template class="header">Location</template>

                <vaadin-grid-column>
                  <template class="header">City</template>
                  <template>[[item.location.city]]</template>
                </vaadin-grid-column>

                <vaadin-grid-column>
                  <template class="header">State</template>
                  <template>[[item.location.state]]</template>
                </vaadin-grid-column>

                <vaadin-grid-column>
                  <template class="header">Street</template>
                  <template><p style="white-space: normal">[[item.location.street]]</p></template>
                </vaadin-grid-column>
            </vaadin-grid-column-group>
        </vaadin-grid>
`;
  }

  static get is() {
      return 'templated-columns'
  }
}
customElements.define(TemplatedColumns.is, TemplatedColumns);
