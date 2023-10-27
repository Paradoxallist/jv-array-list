package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow(size + 1);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(messageIndexOutOfBoundsException(index));
        }
        if (size == elementData.length) {
            grow(size + 1);
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > elementData.length) {
            grow(size + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkToCorrectIndex(index);
        return elementData(index);
    }

    @Override
    public void set(T value, int index) {
        checkToCorrectIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkToCorrectIndex(index);
        final T removedElement = elementData(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        elementData[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("ArrayList don't have such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T elementData(int index) {
        return (T) elementData[index];
    }

    private void checkToCorrectIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(messageIndexOutOfBoundsException(index));
        }
    }

    private void grow(int minCapacity) {
        int newCapacity = Math.max((int)(elementData.length * 1.5f), minCapacity);
        T[] newElementData = (T[])new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        elementData = newElementData;
    }

    private String messageIndexOutOfBoundsException(int index) {
        return "Index " + index + " out of bounds for length" + elementData.length;
    }
}
