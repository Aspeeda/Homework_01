package pages;

import annotations.PathTemplate;
import com.google.inject.Inject;
import components.staticComponents.LessonCardComponent;
import data.Course;
import exceptions.ElementIsNotSingle;
import exceptions.ElementNotFound;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@PathTemplate("$0/$1")
public class CatalogCoursesPage extends AbsBasePage<CatalogCoursesPage> {
    public CatalogCoursesPage(WebDriver driver) {
        super(driver);
    }

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy");

    @Inject
    private LessonCardPage lessonCardPage;
    @Inject
    private LessonCardComponent lessonCardComponent;

    @FindBy(xpath = "//section[./div]//a[contains(@href, '/lessons')]//h6")
    private List<WebElement> lessonItems;

    public void clickLessonByName(String name) {
        List<WebElement> lesson = lessonItems
                .stream()
                .filter(lessonItems -> lessonItems.getText().equalsIgnoreCase(name)).toList();
        if (lesson.isEmpty()) {
            throw new ElementNotFound(name);
        } else if (lesson.size() > 1) {
            throw new ElementIsNotSingle(name);
        }
        lesson.get(0).click();
    }

    public Optional<LocalDate> getEarliestStartDate(List<Course> courses) {
        return courses.stream()
                .map(Course::startDate)
                .min(LocalDate::compareTo);
    }

    public Optional<LocalDate> getLatestStartDate(List<Course> courses) {
        return courses.stream()
                .map(Course::startDate)
                .max(LocalDate::compareTo);
    }

    public List<Course> getCoursesWithEarliestStartDate(List<Course> courses, LocalDate earliestDate) {
        return courses.stream()
                .filter(course -> course.startDate().equals(earliestDate))
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesWithLatestStartDate(List<Course> courses, LocalDate latestDate) {
        return courses.stream()
                .filter(course -> course.startDate().equals(latestDate))
                .collect(Collectors.toList());
    }

    public void verifyCourseDetails(Course course) throws IOException {
        Document doc = Jsoup.connect(course.link()).get();
        Element titleElement = doc.selectFirst("section div > div > h1");
        Element dateElement = doc.selectFirst("section> :nth-child(3) div > div:nth-child(1) > p");

        assertEquals(course.title(), titleElement.text(), "Наименование не соответствует");
        assertEquals(course.startDate().format(dateFormatter).split(",")[0].trim(), dateElement.text(),
                "Дата старта не соответствует");
    }
}


