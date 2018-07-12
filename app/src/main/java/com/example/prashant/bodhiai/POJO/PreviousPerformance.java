package com.example.prashant.bodhiai.POJO;

public class PreviousPerformance {

        private String[] marks;

        private String subject;

        private String[] date;

        public String[] getMarks ()
        {
            return marks;
        }

        public void setMarks (String[] marks)
        {
            this.marks = marks;
        }

        public String getSubject ()
        {
            return subject;
        }

        public void setSubject (String subject)
        {
            this.subject = subject;
        }

        public String[] getDate ()
        {
            return date;
        }

        public void setDate (String[] date)
        {
            this.date = date;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [marks = "+marks+", subject = "+subject+", date = "+date+"]";
        }


}
