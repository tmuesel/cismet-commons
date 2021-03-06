/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.tools;
/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public class Sorter {

    //~ Methods ----------------------------------------------------------------

// -----------------------------------------------------------------------------------------------
    /**
     * ueberpruft ob das array sortiert ist.
     *
     * @param   array  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private static boolean isSorted(final Comparable[] array) {
        if (array.length < 2) {
            return true;
        }

        for (int i = 1; i < array.length; i++) {
            if (array[i - 1].compareTo(array[i]) > 0)   // >
            {
                System.out.println("fehler bei i" + i); // NOI18N
                return false;
            }
        }

        return true;
    }

    /**
     * -------------------------------------------------------------------------------
     *
     * @param  array  DOCUMENT ME!
     */
    public static void insertionSort(final Comparable[] array) {
        insertionSort(array, 0, array.length - 1);
    }

    /**InsertionSort*/

    public static void insertionSort(final Comparable[] array, final int left, final int right) {
        int in;
        int out;

        for (out = left + 1; out <= right; out++) {
            final Comparable tmp = array[out];
            in = out;

            while ((in > left) && (array[in - 1].compareTo(tmp) >= 0)) // >=
            {
                array[in] = array[in - 1];
                --in;
            }

            array[in] = tmp;
        }
    }

//----------------------------------------------------------------------------------------------------------

    /**
     * Quicksort welches bei der Unterschreitung von partitionsize auf InsertionSort umschaltet.
     *
     * @param  array          DOCUMENT ME!
     * @param  left           DOCUMENT ME!
     * @param  right          DOCUMENT ME!
     * @param  partitionSize  DOCUMENT ME!
     */

    private static void quickSort(final Comparable[] array, final int left, final int right, final int partitionSize) {
        if (left >= right) {
            return;
        }

        final int size = right - left + 1;

        if (size < partitionSize) {
            insertionSort(array, left, right);
        } else {
            final Object median = array[right]; // rightMost Element
            final int partition = partitionIt(array, left, right, median);

            quickSort(array, left, partition - 1, partitionSize);
            quickSort(array, partition + 1, right, partitionSize);
        }
    }

    /**
     * ----------------------------------------------------------------------------------------------
     *
     * @param  array  DOCUMENT ME!
     */
    public static void quickSort(final Comparable[] array) {
        quickSort(array, 0, array.length - 1, 16); // no insertion when partition >=16
    }
    /**
     * --------------------------------------------------------------------------- nimmt die unterteilung in die Mengen
     * S< u. S> vor wird von Quicksort verwendet*
     *
     * @param   array  DOCUMENT ME!
     * @param   left   DOCUMENT ME!
     * @param   right  DOCUMENT ME!
     * @param   pivot  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private static int partitionIt(final Comparable[] array, final int left, final int right, final Object pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;

        while (true) {
            while (array[++leftPtr].compareTo(pivot) < 0) { // <
                ;                                           // nop
            }

            while ((array[--rightPtr].compareTo(pivot) > 0) && (rightPtr > 0)) { // >
                ;                                                                // nop
            }

            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(array, leftPtr, rightPtr);
            }
        } // end forever

        swap(array, leftPtr, right);

        return leftPtr;
    }

//----------------------------------------------------------------------------------------------

    /**
     * tauscht 2 Elemente eines Arrays.
     *
     * @param  array  DOCUMENT ME!
     * @param  i      DOCUMENT ME!
     * @param  j      DOCUMENT ME!
     */

    private static void swap(final Object[] array, final int i, final int j) {
        final Object tmp = array[i];

        array[i] = array[j];

        array[j] = tmp;
    }
} // end class sorter
