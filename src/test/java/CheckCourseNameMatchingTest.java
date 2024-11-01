import com.google.inject.Inject;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CatalogCoursesPage;

@ExtendWith(UIExtension.class)
public class CheckCourseNameMatchingTest {

    @Inject
    private CatalogCoursesPage catalogCoursesPage;

    @Test
    public void checkCourseNameMatchingTest() {

        String lessonName = "QA Lead";
        catalogCoursesPage
                .open("catalog", "courses")
                .pageHeaderShouldBeSameAs("Каталог")
                .clickLessonByName(lessonName);
        catalogCoursesPage.pageHeaderShouldBeSameAs(lessonName);
    }
}
