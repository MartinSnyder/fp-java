/*
 *  Apache License
 *  Version 2.0, January 2004
 *
 *  See LICENSE file in project root for details
 */
package com.martinsnyder.fpjava;

interface Expressions {
    static void main(String[] args) {
        final int someInt = 5;

        final int conditionalInt;
        if (someInt % 2 == 0)
            conditionalInt = 17;
        else
            conditionalInt = 20;

        final int expressionInt = (someInt % 2 == 0) ? 17 : 20;

        final String conditionalString;
        switch (conditionalInt) {
            case 0: conditionalString  = "zero";         break;
            case 1: conditionalString  = "one";          break;
            default: conditionalString = "notZeroOrOne"; break;
        }

        final String expressionString =
            switch (conditionalInt) {
                case 0  -> "zero";
                case 1  -> "one";
                default -> "notZeroOrOne";
            };
    }
}