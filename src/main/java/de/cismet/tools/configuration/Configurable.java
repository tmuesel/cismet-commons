/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.tools.configuration;

import org.jdom.Element;

/**
 * Configurable Interface.
 *
 * @author   thorsten.hell@cismet.de
 * @version  $Revision$, $Date$
 */
public interface Configurable {

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME! Always used after masterConfigure
     *
     * @param  parent  configuration.xml
     */
    void configure(Element parent);
    /**
     * DOCUMENT ME!
     *
     * @param  parent  DOCUMENT ME!
     */
    void masterConfigure(Element parent);
    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  NoWriteError  DOCUMENT ME!
     */
    Element getConfiguration() throws NoWriteError;
}
