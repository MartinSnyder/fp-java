/*
 *  Apache License
 *  Version 2.0, January 2004
 *
 *  See LICENSE file in project root for details
 */
package com.martinsnyder.fpjava;

import java.util.Date;

interface VisitorPattern {
    class NaiveEmployee {
        String name;
        Date startDate;
        boolean isCurrentEmployee;
        Date endDate;

        long getMillisecondsOfService() {
            if (isCurrentEmployee) {
                return new Date().getTime() - startDate.getTime();
            } else {
                return endDate.getTime() - startDate.getTime();
            }
        }
    }

    interface EmploymentDatesVisitor {
        void visit(CurrentEmployee ce);
        void visit(TerminatedEmployee te);
    }

    interface EmploymentDates{
        void visit(EmploymentDatesVisitor visitor);
    }
    class CurrentEmployee implements EmploymentDates {
        Date startDate;

        public void visit(EmploymentDatesVisitor visitor) { visitor.visit(this); }
    }
    class TerminatedEmployee implements EmploymentDates {
        Date startDate;
        Date endDate;

        public void visit(EmploymentDatesVisitor visitor) { visitor.visit(this); }
    }

    class MillisecondsOfServiceVisitor implements EmploymentDatesVisitor {
        long millis;

        @Override
        public void visit(CurrentEmployee ce) {
            millis = new Date().getTime() - ce.startDate.getTime();
        }

        @Override
        public void visit(TerminatedEmployee te) {
            millis = te.endDate.getTime() - te.startDate.getTime();
        }
    }

    class EmployeeWithADT {
        String name;
        EmploymentDates employmentDates;

        long getMillisecondsOfService() {
            MillisecondsOfServiceVisitor visitor = new MillisecondsOfServiceVisitor();
            employmentDates.visit(visitor);
            return visitor.millis;
        }
    }
}
