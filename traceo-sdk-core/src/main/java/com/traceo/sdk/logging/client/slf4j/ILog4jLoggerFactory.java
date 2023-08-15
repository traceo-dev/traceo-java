package org.traceo.sdk.logging.client.slf4j;

import org.slf4j.Logger;

public interface ILog4jLoggerFactory {
    Logger create(String name);

    Logger getLogger();
}
