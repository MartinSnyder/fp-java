package com.martinsnyder.fpjava;

import java.util.List;

public interface ImmutableCollections {
    static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3, 4, 5);
        intList.add(6);
    }
}
