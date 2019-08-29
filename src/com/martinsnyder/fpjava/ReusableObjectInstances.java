/*
 *  Apache License
 *  Version 2.0, January 2004
 *
 *  See LICENSE file in project root for details
 */
package com.martinsnyder.fpjava;

interface ReusableObjectInstances {
    static String equalityString(Object o1, Object o2) {
        if (o1 == o2) {
            return "instances are the same";
        }
        else if (o1.equals(o2)) {
           return "are equal but not the same";
        }
        else {
            return "are not equal";
        }
    }

    static void main(String[] args) {
        String one = "one";
        String two = "two";
        String compiledString = "onetwo";
        String runtimeString = one + two;
        String optimizedString = runtimeString.intern();

        System.out.println("compiledString and runtimeString " + equalityString(compiledString, runtimeString));
        System.out.println("compiledString and optimizedString " + equalityString(compiledString, optimizedString));
    }
}