/*
 *  Apache License
 *  Version 2.0, January 2004
 *
 *  See LICENSE file in project root for details
 */
package com.martinsnyder.fpjava;

import java.util.List;

interface ImmutableCollections {
    static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3, 4, 5);
        intList.add(6);
    }
}
