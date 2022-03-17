/*******************************************************************************
 *     Architect - A free 2D/3D home and interior designer
 *     Copyright (C) 2021, 2022  Daniel Höh
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>
 *******************************************************************************/
package de.dh.utils.fx.io.utils;


import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Vector;


/**
 * Resizable-array implementation of the <tt>List&lt;Float&gt;</tt> interface.  Implements all optional list operations,
 * and doesn't permit <tt>null</tt>s.  In addition to implementing the <tt>List</tt> interface, this class provides
 * methods to manipulate the size of the array that is used internally to store the list.  (This class is roughly
 * equivalent to <tt>Vector</tt>, except that it is unsynchronized.)
 *
 * <p>The <tt>size</tt>, <tt>isEmpty</tt>, <tt>get</tt>, <tt>set</tt>, <tt>iterator</tt>, and <tt>listIterator</tt>
 * operations run in constant time.  The <tt>add</tt> operation runs in <i>amortized constant time</i>, that is, adding
 * n elements requires O(n) time.  All of the other operations run in linear time (roughly speaking).  The constant
 * factor is low compared to that for the <tt>LinkedList</tt> implementation.
 * </p>
 * <p>Each <tt>ArrayList</tt> instance has a <i>capacity</i>.  The capacity is the size of the array used to store the
 * elements in the list.  It is always at least as large as the list size.  As elements are added to an ArrayList, its
 * capacity grows automatically.  The details of the growth policy are not specified beyond the fact that adding an
 * element has constant amortized time cost.
 * </p>
 * <p>An application can increase the capacity of an <tt>ArrayList</tt> instance before adding a large number of
 * elements using the <tt>ensureCapacity</tt> operation.  This may reduce the amount of incremental reallocation.
 * </p>
 * <p><strong>Note that this implementation is not synchronized.</strong> If multiple threads access an
 * <tt>ArrayList</tt> instance concurrently, and at least one of the threads modifies the list structurally, it
 * <i>must</i> be synchronized externally.  (A structural modification is any operation that adds or deletes one or more
 * elements, or explicitly resizes the backing array; merely setting the value of an element is not a structural
 * modification.)  This is typically accomplished by synchronizing on some object that naturally encapsulates the list.
 * </p>
 * If no such object exists, the list should be "wrapped" using the {@link Collections#synchronizedList
 * Collections.synchronizedList} method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the list:<pre>
 *   List list = Collections.synchronizedList(new ArrayList(...));</pre>
 * </p>
 * <p><a name="fail-fast"/> The iterators returned by this class's {@link #iterator() iterator} and {@link
 * #listIterator(int) listIterator} methods are <em>fail-fast</em>: if the list is structurally modified at any time
 * after the iterator is created, in any way except through the iterator's own {@link ListIterator#remove() remove} or
 * {@link ListIterator#add(Object) add} methods, the iterator will throw a {@link ConcurrentModificationException}.
 * Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary,
 * non-deterministic behavior at an undetermined time in the future.
 * </p>
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to
 * make any hard guarantees in the presence of unsynchronized concurrent modification.  Fail-fast iterators throw {@code
 * ConcurrentModificationException} on a best-effort basis. Therefore, it would be wrong to write a program that
 * depended on this exception for its correctness:  <i>the fail-fast behavior of iterators should be used only to detect
 * bugs.</i>
 * </p>
 * <p>This class is a member of the <a href="{@docRoot}/../technotes/guides/collections/index.html"> Java Collections
 * Framework</a>.
 *
 * @see Collection
 * @see List
 * @see LinkedList
 * @see Vector
 *
 * TODO replace with ObservableFloatArray
 */
public class FloatArrayList extends AbstractList<Float>
        implements List<Float>, RandomAccess, Cloneable, java.io.Serializable {

    /**
     * The array buffer into which the elements of the ArrayList are stored. The capacity of the ArrayList is the length
     * of this array buffer.
     */
    private transient float[] elementData;

    /**
     * The size of the ArrayList (the number of elements it contains).
     *
     * @serial
     */
    private int size;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity is negative
     */
    public FloatArrayList(int initialCapacity) {
        super();
        if (initialCapacity < 0) {
            throw new IllegalArgumentException(
                    "Illegal Capacity: " +
                            initialCapacity);
        }
        this.elementData = new float[initialCapacity];
    }

    /** Constructs an empty list with an initial capacity of ten. */
    public FloatArrayList() {
        this(10);
    }

    /**
     * Constructs a list containing the elements of the specified collection, in the order they are returned by the
     * collection's iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public FloatArrayList(Collection<? extends Float> c) {
        elementData = new float[c.size()];
        int i = 0;
        for (Float d : c) {
            elementData[i] = d;
            i++;
        }
        size = elementData.length;
    }

    /**
     * Trims the capacity of this <tt>ArrayList</tt> instance to be the list current size.  An application can use this
     * operation to minimize the storage of an <tt>ArrayList</tt> instance.
     */
    public void trimToSize() {
        modCount++;
        int oldCapacity = elementData.length;
        if (size < oldCapacity) {
            elementData = Arrays.copyOf(elementData, size);
        }
    }

    /**
     * Increases the capacity of this <tt>ArrayList</tt> instance, if necessary, to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > 0) {
            ensureCapacityInternal(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        modCount++;
        // overflow-conscious code
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * The maximum size of array to allocate. Some VMs reserve some header words in an array. Attempts to allocate
     * larger arrays may result in OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increases the capacity to ensure that it can hold at least the number of elements specified by the minimum
     * capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    @Override public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element. More formally, returns <tt>true</tt> if and
     * only if this list contains at least one element <tt>e</tt> such that <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     */
    @Override public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not
     * contain the element. More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>, or -1 if there is no such index.
     */
    @Override public int indexOf(Object o) {
        if (o instanceof Float) {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not
     * contain the element. More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>, or -1 if there is no such index.
     */
    @Override public int lastIndexOf(Object o) {
        if (o instanceof Float) {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Returns a shallow copy of this <tt>ArrayList</tt> instance.  (The elements themselves are not copied.)
     *
     * @return a clone of this <tt>ArrayList</tt> instance
     */
    @Override public Object clone() {
        try {
            FloatArrayList v = (FloatArrayList) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     * </p>
     * <p>The returned array will be "safe" in that no references to it are maintained by this list.  (In other words,
     * this method must allocate a new array).  The caller is thus free to modify the returned array.
     * </p>
     * <p>This method acts as bridge between array-based and collection-based APIs.
     *
     * @return an array containing all of the elements in this list in proper sequence
     */
    @Override public Object[] toArray() {
        Float[] array = new Float[size];
        for (int i = 0; i < size; i++) {
            array[i] = elementData[i];
        }
        return array;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element); the
     * runtime type of the returned array is that of the specified array.  If the list fits in the specified array, it
     * is returned therein.  Otherwise, a new array is allocated with the runtime type of the specified array and the
     * size of this list.
     * </p>
     * <p>If the list fits in the specified array with room to spare (i.e., the array has more elements than the list),
     * the element in the array immediately following the end of the collection is set to <tt>null</tt>.  (This is
     * useful in determining the length of the list <i>only</i> if the caller knows that the list does not contain any
     * null elements.)
     *
     * @param a the array into which the elements of the list are to be stored, if it is big enough; otherwise, a new
     *          array of the same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list
     * @throws ArrayStoreException  if the runtime type of the specified array is not a supertype of the runtime type of
     *                              every element in this list
     * @throws NullPointerException if the specified array is null
     */
    @SuppressWarnings("unchecked")
    @Override public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public float[] toFloatArray() {
        float[] res = new float[size];
        System.arraycopy(elementData, 0, res, 0, size);
        return res;
    }

    // Positional Access Operations

    Float elementData(int index) {
        return elementData[index];
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override public Float get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override public Float set(int index, Float element) {
        rangeCheck(index);

        Float oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    @Override public boolean add(Float e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override public void add(int index, Float element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(
                elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts
     * one from their indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override public Float remove(int index) {
        rangeCheck(index);

        modCount++;
        Float oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(
                    elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = 0; // Forget the item completely

        return oldValue;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.  If the list does not
     * contain the element, it is unchanged.  More formally, removes the element with the lowest index <tt>i</tt> such
     * that <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt> (if such an element exists).
     * Returns <tt>true</tt> if this list contained the specified element (or equivalently, if this list changed as a
     * result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     */
    @Override public boolean remove(Object o) {
        if (o instanceof Float) {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    /*
     * Private remove method that skips bounds checking and does not
     * return the value removed.
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(
                    elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = 0; // Forget the item completely
    }

    /** Removes all of the elements from this list.  The list will be empty after this call returns. */
    @Override public void clear() {
        modCount++;

        // Forget the items completely
        for (int i = 0; i < size; i++)
            elementData[i] = 0;

        size = 0;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are
     * returned by the specified collection's Iterator.  The behavior of this operation is undefined if the specified
     * collection is modified while the operation is in progress.  (This implies that the behavior of this call is
     * undefined if the specified collection is this list, and this list is nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override public boolean addAll(Collection<? extends Float> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * Inserts all of the elements in the specified collection into this list, starting at the specified position.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their
     * indices).  The new elements will appear in the list in the order that they are returned by the specified
     * collection's iterator.
     *
     * @param index index at which to insert the first element from the specified collection
     * @param c     collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException      if the specified collection is null
     */
    @Override public boolean addAll(int index, Collection<? extends Float> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(
                    elementData, index, elementData, index + numNew,
                    numMoved);

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * Removes from this list all of the elements whose index is between {@code fromIndex}, inclusive, and {@code
     * toIndex}, exclusive. Shifts any succeeding elements to the left (reduces their index). This call shortens the
     * list by {@code (toIndex - fromIndex)} elements. (If {@code toIndex==fromIndex}, this operation has no effect.)
     *
     * @throws IndexOutOfBoundsException if {@code fromIndex} or {@code toIndex} is out of range ({@code fromIndex < 0
     *                                   || fromIndex >= size() || toIndex > size() || toIndex < fromIndex})
     */
    @Override protected void removeRange(int fromIndex, int toIndex) {
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(
                elementData, toIndex, elementData, fromIndex,
                numMoved);

        // Forget the item completely
        int newSize = size - (toIndex - fromIndex);
        while (size != newSize)
            elementData[--size] = 0;
    }

    /**
     * Checks if the given index is in range.  If not, throws an appropriate runtime exception.  This method does *not*
     * check if the index is negative: It is always used immediately prior to an array access, which throws an
     * ArrayIndexOutOfBoundsException if index is negative.
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /** A version of rangeCheck used by add and addAll. */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message. Of the many possible refactorings of the error handling
     * code, this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * Removes from this list all of its elements that are contained in the specified collection.
     *
     * @param c collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     * @throws ClassCastException   if the class of an element of this list is incompatible with the specified
     *                              collection (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this list contains a null element and the specified collection does not permit
     *                              null elements (<a href="Collection.html#optional-restrictions">optional</a>), or if
     *                              the specified collection is null
     * @see Collection#contains(Object)
     */
    @Override public boolean removeAll(Collection<?> c) {
        return batchRemove(c, false);
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection.  In other words, removes
     * from this list all of its elements that are not contained in the specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return {@code true} if this list changed as a result of the call
     * @throws ClassCastException   if the class of an element of this list is incompatible with the specified
     *                              collection (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this list contains a null element and the specified collection does not permit
     *                              null elements (<a href="Collection.html#optional-restrictions">optional</a>), or if
     *                              the specified collection is null
     * @see Collection#contains(Object)
     */
    @Override public boolean retainAll(Collection<?> c) {
        return batchRemove(c, true);
    }

    private boolean batchRemove(Collection<?> c, boolean complement) {
        final float[] localElementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++)
                if (c.contains(localElementData[r]) == complement)
                    localElementData[w++] = localElementData[r];
        } finally {
            // Preserve behavioral compatibility with AbstractCollection,
            // even if c.contains() throws.
            if (r != size) {
                System.arraycopy(
                        localElementData, r,
                        localElementData, w,
                        size - r);
                w += size - r;
            }
            if (w != size) {
                for (int i = w; i < size; i++)
                    localElementData[i] = 0;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Save the state of the <tt>ArrayList</tt> instance to a stream (that is, serialize it).
     *
     * @serialData The length of the array backing the <tt>ArrayList</tt> instance is emitted (int), followed by all of
     * its elements (each an <tt>Object</tt>) in the proper order.
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out array length
        s.writeInt(elementData.length);

        // Write out all elements in the proper order.
        for (int i = 0; i < size; i++)
            s.writeObject(elementData[i]);

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }

    }

    /** Reconstitute the <tt>ArrayList</tt> instance from a stream (that is, deserialize it). */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in array length and allocate array
        int arrayLength = s.readInt();
        float[] a = elementData = new float[arrayLength];

        // Read in all elements in the proper order.
        for (int i = 0; i < size; i++)
            a[i] = (Float) s.readObject();
    }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position
     * in the list. The specified index indicates the first element that would be returned by an initial call to {@link
     * ListIterator#next next}. An initial call to {@link ListIterator#previous previous} would return the element with
     * the specified index minus one.
     * </p>
     * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override public ListIterator<Float> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index);
        return new ListItr(index);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence).
     * </p>
     * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @see #listIterator(int)
     */
    @Override public ListIterator<Float> listIterator() {
        return new ListItr(0);
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * </p>
     * <p>The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override public Iterator<Float> iterator() {
        return new Itr();
    }

    /** An optimized version of AbstractList.Itr */
    private class Itr implements Iterator<Float> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        @Override public boolean hasNext() {
            return cursor != size;
        }

        @Override public Float next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            float[] localElementData = FloatArrayList.this.elementData;
            if (i >= localElementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return localElementData[lastRet = i];
        }

        @Override public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                FloatArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    /** An optimized version of AbstractList.ListItr */
    private class ListItr extends Itr implements ListIterator<Float> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        @Override public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override public int nextIndex() {
            return cursor;
        }

        @Override public int previousIndex() {
            return cursor - 1;
        }

        @Override public Float previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            float[] localElementData = FloatArrayList.this.elementData;
            if (i >= localElementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return localElementData[lastRet = i];
        }

        @Override public void set(Float e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                FloatArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override public void add(Float e) {
            checkForComodification();

            try {
                int i = cursor;
                FloatArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * Returns a view of the portion of this list between the specified {@code fromIndex}, inclusive, and {@code
     * toIndex}, exclusive.  (If {@code fromIndex} and {@code toIndex} are equal, the returned list is empty.)  The
     * returned list is backed by this list, so non-structural changes in the returned list are reflected in this list,
     * and vice-versa. The returned list supports all of the optional list operations.
     * </p>
     * <p>This method eliminates the need for explicit range operations (of the sort that commonly exist for arrays).
     * Any operation that expects a list can be used as a range operation by passing a subList view instead of a whole
     * list.  For example, the following idiom removes a range of elements from a list:
     * <pre>
     *      list.subList(from, to).clear();
     * </pre>
     * Similar idioms may be constructed for {@link #indexOf(Object)} and {@link #lastIndexOf(Object)}, and all of the
     * algorithms in the {@link Collections} class can be applied to a subList.
     * </p>
     * <p>The semantics of the list returned by this method become undefined if the backing list (i.e., this list) is
     * <i>structurally modified</i> in any way other than via the returned list.  (Structural modifications are those
     * that change the size of this list, or otherwise perturb it in such a fashion that iterations in progress may
     * yield incorrect results.)
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws IllegalArgumentException  {@inheritDoc}
     */
    @Override public List<Float> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return new FloatArrayList.SubList(this, 0, fromIndex, toIndex);
    }

    static void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException(
                    "fromIndex(" + fromIndex +
                            ") > toIndex(" + toIndex + ")");
    }

    private class SubList extends FloatArrayList implements RandomAccess {
        private final FloatArrayList parent;
        private final int parentOffset;
        private final int offset;
        int size;

        SubList(
                FloatArrayList parent,
                int offset, int fromIndex, int toIndex) {
            this.parent = parent;
            this.parentOffset = fromIndex;
            this.offset = offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = FloatArrayList.this.modCount;
        }

        @Override
        public float[] toFloatArray() {
            float[] res = new float[size];
            System.arraycopy(elementData, offset, res, 0, size);
            return res;
        }

        @Override public Float set(int index, Float e) {
            rangeCheck(index);
            checkForComodification();
            Float oldValue = FloatArrayList.this.elementData(offset + index);
            FloatArrayList.this.elementData[offset + index] = e;
            return oldValue;
        }

        @Override public Float get(int index) {
            rangeCheck(index);
            checkForComodification();
            return FloatArrayList.this.elementData(offset + index);
        }

        @Override public int size() {
            checkForComodification();
            return this.size;
        }

        @Override public void add(int index, Float e) {
            rangeCheckForAdd(index);
            checkForComodification();
            parent.add(parentOffset + index, e);
            this.modCount = parent.modCount;
            this.size++;
        }

        @Override public Float remove(int index) {
            rangeCheck(index);
            checkForComodification();
            Float result = parent.remove(parentOffset + index);
            this.modCount = parent.modCount;
            this.size--;
            return result;
        }

        @Override protected void removeRange(int fromIndex, int toIndex) {
            checkForComodification();
            parent.removeRange(
                    parentOffset + fromIndex,
                    parentOffset + toIndex);
            this.modCount = parent.modCount;
            this.size -= toIndex - fromIndex;
        }

        @Override public boolean addAll(Collection<? extends Float> c) {
            return addAll(this.size, c);
        }

        @Override public boolean addAll(int index, Collection<? extends Float> c) {
            rangeCheckForAdd(index);
            int cSize = c.size();
            if (cSize == 0)
                return false;

            checkForComodification();
            parent.addAll(parentOffset + index, c);
            this.modCount = parent.modCount;
            this.size += cSize;
            return true;
        }

        @Override public Iterator<Float> iterator() {
            return listIterator();
        }

        @Override public ListIterator<Float> listIterator(final int index) {
            checkForComodification();
            rangeCheckForAdd(index);
            final int iteratorOffset = this.offset;

            return new ListIterator<>() {
                int cursor = index;
                int lastRet = -1;
                int expectedModCount = FloatArrayList.this.modCount;

                @Override public boolean hasNext() {
                    return cursor != FloatArrayList.SubList.this.size;
                }

                @Override public Float next() {
                    checkForComodification();
                    int i = cursor;
                    if (i >= FloatArrayList.SubList.this.size)
                        throw new NoSuchElementException();
                    float[] localElementData = FloatArrayList.this.elementData;
                    if (iteratorOffset + i >= localElementData.length)
                        throw new ConcurrentModificationException();
                    cursor = i + 1;
                    return localElementData[iteratorOffset + (lastRet = i)];
                }

                @Override public boolean hasPrevious() {
                    return cursor != 0;
                }

                @Override public Float previous() {
                    checkForComodification();
                    int i = cursor - 1;
                    if (i < 0)
                        throw new NoSuchElementException();
                    float[] localElementData = FloatArrayList.this.elementData;
                    if (iteratorOffset + i >= localElementData.length)
                        throw new ConcurrentModificationException();
                    cursor = i;
                    return localElementData[iteratorOffset + (lastRet = i)];
                }

                @Override public int nextIndex() {
                    return cursor;
                }

                @Override public int previousIndex() {
                    return cursor - 1;
                }

                @Override public void remove() {
                    if (lastRet < 0)
                        throw new IllegalStateException();
                    checkForComodification();

                    try {
                        FloatArrayList.SubList.this.remove(lastRet);
                        cursor = lastRet;
                        lastRet = -1;
                        expectedModCount = FloatArrayList.this.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override public void set(Float e) {
                    if (lastRet < 0)
                        throw new IllegalStateException();
                    checkForComodification();

                    try {
                        FloatArrayList.this.set(iteratorOffset + lastRet, e);
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override public void add(Float e) {
                    checkForComodification();

                    try {
                        int i = cursor;
                        FloatArrayList.SubList.this.add(i, e);
                        cursor = i + 1;
                        lastRet = -1;
                        expectedModCount = FloatArrayList.this.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                private void checkForComodification() {
                    if (expectedModCount != FloatArrayList.this.modCount)
                        throw new ConcurrentModificationException();
                }
            };
        }

        @Override public List<Float> subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size);
            return new FloatArrayList.SubList(this, offset, fromIndex, toIndex);
        }

        private void rangeCheck(int index) {
            if (index < 0 || index >= this.size)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private void rangeCheckForAdd(int index) {
            if (index < 0 || index > this.size)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private String outOfBoundsMsg(int index) {
            return "Index: " + index + ", Size: " + this.size;
        }

        private void checkForComodification() {
            if (FloatArrayList.this.modCount != this.modCount)
                throw new ConcurrentModificationException();
        }
    }
}
