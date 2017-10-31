/**
 * The folder contains sorting algorithms for the container
 * NewContainerOfStrings. These algorithms: </br>
 * </br>
 * <b>BubbleClassicSort</b> contains the realization of classic algorithm of
 * bubble sort. </br>
 * The essence of bubble sort is in comparing two 'neighbours' in the array.
 * </br>
 * In each step one elements gets its place.</br>
 * </br>
 * <b>BubbleModifiedSort</b> contains the realization of modified bubble sorting
 * algorithm. </br>
 * The essence of bubble sort is in comparing two 'neighbours' in the array. The
 * modification's essence is in stop parameter, that enables to compare elements
 * only if its value is <i>true</i></br>
 * </br>
 * <b>ExchangeSampleSort</b> contains the realization of the sortinf algorithm
 * with the exchange simple sample. Is one of the Sample sorting
 * algorithms.</br>
 * </br>
 * <b>HeapSort</b> contains the realization of the sorting algorithm with a
 * heap.Heapsort can be thought of as an improved selection sort: like that
 * algorithm, it divides its input into a sorted and an unsorted region, and it
 * iteratively shrinks the unsorted region by extracting the largest element and
 * moving that to the sorted region. The improvement consists of the use of a
 * heap data structure rather than a linear-time search to find the
 * maximum.</br>
 * </br>
 * <b>InsertionSort</b> contains the realization of the sorting algorithm with
 * insertions. Insertion sort is a simple sorting algorithm that builds the
 * final sorted array (or list) one item at a time. It is much less efficient on
 * large lists than more advanced algorithms such as quicksort, heapsort.
 * However, insertion sort provides several advantages.</br>
 * </br>
 * <b>PocketSort</b> contains the realization of the pocket sorting
 * algorithm.</br>
 * </br>
 * <b>QuickSort</b> contains the realization of the quick sort algorithm.
 * Quicksort is a comparison sort, meaning that it can sort items of any type
 * for which a "less-than" relation (formally, a total order) is defined. In
 * efficient implementations it is not a stable sort, meaning that the relative
 * order of equal sort items is not preserved. Quicksort can operate in-place on
 * an array, requiring small additional amounts of memory to perform the
 * sorting.</br>
 * </br>
 * <b>ShakerSort</b> contains the realization of Shaker sorting algorithm. is a
 * variation of bubble sort that is both a stable sorting algorithm and a
 * comparison sort. The algorithm differs from a bubble sort in that it sorts in
 * both directions on each pass through the list. This sorting algorithm is only
 * marginally more difficult to implement than a bubble sort, and solves the
 * problem of turtles in bubble sorts. It provides only marginal performance
 * improvements, and does not improve asymptotic performance; like the bubble
 * sort, it is not of practical interest (insertion sort is preferred for simple
 * sorts), though it finds some use in education.</br>
 * </br>
 * <b>ShellSort</b> contains the realization of Shell sorting algorithm.
 * Shellsort, also known as Shell sort or Shell's method, is an in-place
 * comparison sort. It can be seen as either a generalization of sorting by
 * exchange (bubble sort) or sorting by insertion (insertion sort). The method
 * starts by sorting pairs of elements far apart from each other, then
 * progressively reducing the gap between elements to be compared. Starting with
 * far apart elements, it can move some out-of-place elements into position
 * faster than a simple nearest neighbor exchange. Donald Shell published the
 * first version of this sort in 1959. The running time of Shellsort is heavily
 * dependent on the gap sequence it uses. For many practical variants,
 * determining their time complexity remains an open problem.</br>
 * </br>
 * <b>SimpleSample</b> contains the realization of sorting algorithm with the
 * simple sample. Is one of the SampleSorting algorithms. Samplesort is a
 * sorting algorithm that is a divide and conquer algorithm often used in
 * parallel processing systems.</br>
 * </br>
 * 
 * 
 * All these classes extend <b>ForSort</b> class and implement
 * <b>SortAbstract</b> interface.
 */
package ua.khpi.oop.pavlova06.util.sortAlgorithms;