package com.example.prashant.bodhiai.POJO;

public class TestDetails {
       private String id;

       private String[] topics;

       private String subject;

       private String num_questions;

       private String published;

       private String creator;

       public String getId ()
       {
           return id;
       }

       public void setId (String id)
       {
           this.id = id;
       }

       public String[] getTopics ()
       {
           return topics;
       }

       public void setTopics (String[] topics)
       {
           this.topics = topics;
       }

       public String getSubject ()
       {
           return subject;
       }

       public void setSubject (String subject)
       {
           this.subject = subject;
       }

       public String getNum_questions ()
       {
           return num_questions;
       }

       public void setNum_questions (String num_questions)
       {
           this.num_questions = num_questions;
       }

       public String getPublished ()
       {
           return published;
       }

       public void setPublished (String published)
       {
           this.published = published;
       }

       public String getCreator ()
       {
           return creator;
       }

       public void setCreator (String creator)
       {
           this.creator = creator;
       }

       @Override
       public String toString()
       {
           return "ClassPojo [id = "+id+", topics = "+topics+", subject = "+subject+", num_questions = "+num_questions+", published = "+published+", creator = "+creator+"]";
       }
   }




