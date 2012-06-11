/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.remote;

import org.openide.util.Lookup;

import java.util.*;

/**
 * DOCUMENT ME!
 *
 * @author   bfriedrich
 * @version  $Revision$, $Date$
 */
public final class RESTRemoteControlMethodRegistry {

    //~ Static fields/initializers ---------------------------------------------

    private static final Integer DEFAULT_PORT_INDICATOR = Integer.valueOf(-1);

    private static final Map<Integer, List<RESTRemoteControlMethod>> portMapping =
        new HashMap<Integer, List<RESTRemoteControlMethod>>();

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new RESTRemoteControlMethodRegistry object.
     */
    private RESTRemoteControlMethodRegistry() {
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   defaultPort  DOCUMENT ME!
     *
     * @throws  IllegalStateException  DOCUMENT ME!
     */
    public static synchronized void gatherRemoteMethods(final int defaultPort) {
        if (!portMapping.isEmpty()) {
            throw new IllegalStateException("RESTRemoteControlMethods have already been collected. "
                        + "Call RESTRemoteControlMethodRegistry.clear() to enable new gathering");
        }

        final Lookup lookUp = Lookup.getDefault();
        final Lookup.Result<RESTRemoteControlMethod> result = lookUp.lookupResult(RESTRemoteControlMethod.class);
        final Collection<? extends RESTRemoteControlMethod> allRemoteMethods = result.allInstances();

        // group REST remote control methods by port

        List<RESTRemoteControlMethod> methods;
        Integer port;
        for (final RESTRemoteControlMethod m : allRemoteMethods) {
            port = Integer.valueOf(m.getPort());
            if (DEFAULT_PORT_INDICATOR.equals(port)) {
                port = Integer.valueOf(defaultPort);
            }

            methods = portMapping.get(port);

            if (methods == null) {
                methods = new ArrayList<RESTRemoteControlMethod>();
                portMapping.put(port, methods);
            }

            methods.add(m);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   port  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static synchronized List<RESTRemoteControlMethod> getMethodsForPort(final int port) {
        final List<RESTRemoteControlMethod> methods = portMapping.get(port);
        return (methods == null) ? null : new ArrayList<RESTRemoteControlMethod>(methods);
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static synchronized Set<Integer> getMethodPorts() {
        return new HashSet<Integer>(portMapping.keySet());
    }

    /**
     * DOCUMENT ME!
     */
    public static synchronized void clear() {
        portMapping.clear();
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static synchronized boolean hasMethodsInformation() {
        return portMapping.isEmpty();
    }
}
