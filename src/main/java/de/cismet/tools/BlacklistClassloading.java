/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.tools;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * DOCUMENT ME!
 *
 * @author   srichter
 * @version  $Revision$, $Date$
 */
public final class BlacklistClassloading {

    //~ Static fields/initializers ---------------------------------------------

    private static final Logger LOG = Logger.getLogger(BlacklistClassloading.class);
    private static final Map<String, Object> blacklist = new HashMap<String, Object>();

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new BlacklistClassloading object.
     *
     * @throws  AssertionError  DOCUMENT ME!
     */
    private BlacklistClassloading() {
        throw new AssertionError();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   classname  canonical classname to load
     *
     * @return  the class to load or null if class is not found.
     */
    public static Class<?> forName(final String classname) {
        if (classname != null) {
            final StringBuilder classNameWithLoaderBuilder = new StringBuilder(classname);
            classNameWithLoaderBuilder.append("@").append(Thread.currentThread().getContextClassLoader());
            final String classIdentity = classNameWithLoaderBuilder.toString();
            if (!blacklist.containsKey(classIdentity)) {
                try {
                    return Class.forName(classname);
                } catch (final ClassNotFoundException ex) {
                    blacklist.put(classIdentity, null);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Could not load class " + classIdentity + "! Added classname to blacklist", ex);
                    }
                }
            } else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Did not load Class " + classname + " as it is on the blacklist!"); // NOI18N
                }
            }
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Classname to load was null!");                                         // NOI18N
            }
        }

        return null;
    }
}
