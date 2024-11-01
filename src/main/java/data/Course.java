package data;

import java.time.LocalDate;

public record Course(String title, LocalDate startDate, String link) {
}

/**Джава предложила обернуть в record*/
//public class Course {
//    private final String title;
//    private final LocalDate startDate;
//    private final String link;
//
//    public Course(String title, LocalDate startDate, String link) {
//        this.title = title;
//        this.startDate = startDate;
//        this.link = link;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public String getLink() {
//        return link;
//    }