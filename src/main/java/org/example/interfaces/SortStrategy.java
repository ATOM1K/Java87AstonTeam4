package org.example.interfaces;

import java.util.List;

public interface SortStrategy<T> {
    List<T> sort(List<T> items);
}
