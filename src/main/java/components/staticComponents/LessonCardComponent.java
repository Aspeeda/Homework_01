package components.staticComponents;

import annotations.components.Component;
import components.AbsComponent;
import data.Course;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component("css:section[./div]//a[contains(@href, '/lessons')]")
public class LessonCardComponent extends AbsComponent<LessonCardComponent> {
    public LessonCardComponent(WebDriver driver) {
        super(driver);
    }

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy");


    @FindBy(xpath = "//section[./div]//a[contains(@href, '/lessons')]")
    private List<WebElement> lessonCards;

    public List<Course> getCourses() {
        return lessonCards.stream().map(card -> {
            String title = card.findElement
                    (By.xpath(".//h6")).getText();
            String dateText = card.findElement(By.xpath(
                    (".//div[contains(text(), 'месяц')]"))).getText().split("·")[0].trim();
            LocalDate startDate = LocalDate.parse(dateText, dateFormatter);
            String courseLink = card.getAttribute("href");
            return new Course(title, startDate, courseLink);
        }).toList();
    }

}
