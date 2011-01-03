/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cismet.tools;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Cached ThreadPool for all (Swing)Workers in one cismet application.
 *
 * @author   srichter
 * @version  $Revision$, $Date$
 */
public final class CismetThreadPool {

    //~ Static fields/initializers ---------------------------------------------

    public static final int NUMBER_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService WORKER_POOL = Executors.newCachedThreadPool();

    //~ Methods ----------------------------------------------------------------

    /**
     * Executes the given runnable in the applications common cached threadpool. Notice: It is smarter to pass plain
     * Runnables instead of Threads as arguments, as one purpose of this pool is minimizing thread creation overhead.
     *
     * @param  command  DOCUMENT ME!
     */
    public static void execute(final Runnable command) {
        WORKER_POOL.execute(command);
    }

    /**
     * DOCUMENT ME!
     *
     * @param   command  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Future<?> submit(final Runnable command) {
        return WORKER_POOL.submit(command);
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static List<Runnable> shutdownNow() {
        return WORKER_POOL.shutdownNow();
    }

    /**
     * DOCUMENT ME!
     */
    public static void shutdown() {
        WORKER_POOL.shutdown();
    }
}
