package pages;

import annotations.PathTemplate;
import org.openqa.selenium.WebDriver;

@PathTemplate("lessons/$s")
public class LessonCardPage extends AbsBasePage<LessonCardPage> {
    public LessonCardPage(WebDriver driver) {
        super(driver);
    }
}

