package com.martinsnyder.fpjava;

public class Expressions {
    public static void expressions() {
        final int someInt = 5;

        final int conditionalInt;
        if (someInt % 2 == 0)
            conditionalInt = 17;
        else
            conditionalInt = 20;

        final int conditionalInt2 =  (someInt % 2 == 0) ? 17 : 20;

        final String conditionalString;
        switch (conditionalInt) {
            case 0:
                conditionalString = "zero";
                break;

            case 1:
                conditionalString = "one";
                break;

            default:
                conditionalString = "notZeroOrOne";
                break;
        }
    }
}