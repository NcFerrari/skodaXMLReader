package lp.be.service;

import org.apache.logging.log4j.Logger;

public interface LoggerService {
    /**
     * For logging, you can use methods like:
     * <ul>
     *     <li>trace()</li>
     *     <li>debug()</li>
     *     <li>info()</li>
     *     <li>warn()</li>
     *     <li>error()</li>
     *     <li>fatal()</li>
     * </ul>
     *
     * @return Logger for print message into output
     */
    Logger getLog();
}
