import com.google.inject.Inject;
import components.staticComponents.LessonCardComponent;
import data.Course;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CatalogCoursesPage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(UIExtension.class)
public class CheckAllEarliestAndLatestCoursesTest {

    @Inject
    private CatalogCoursesPage catalogCoursesPage;
    @Inject
    private LessonCardComponent lessonCardComponent;

    @Test
    public void checkAllEarliestAndLatestCoursesTest() throws IOException {
        catalogCoursesPage
                .open("catalog", "courses")
                .pageHeaderShouldBeSameAs("Каталог");

        List<Course> courses = lessonCardComponent.getCourses();

        Optional<LocalDate> earliestDate = catalogCoursesPage.getEarliestStartDate(courses);
        Optional<LocalDate> latestDate = catalogCoursesPage.getLatestStartDate(courses);
        assertFalse(earliestDate.isEmpty(), "Не найдены курсы с самой ранней датой старта");
        assertFalse(latestDate.isEmpty(), "Не найдены курсы с самой поздней датой старта");

        List<Course> earliestCourses = catalogCoursesPage.getCoursesWithEarliestStartDate(courses, earliestDate.get());
        List<Course> latestCourses = catalogCoursesPage.getCoursesWithLatestStartDate(courses, latestDate.get());

        for (Course course : earliestCourses) {
            catalogCoursesPage.verifyCourseDetails(course);
        }
        for (Course course : latestCourses) {
            catalogCoursesPage.verifyCourseDetails(course);
        }
    }
}
