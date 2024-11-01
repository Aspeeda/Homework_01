import com.google.inject.Inject;
import components.staticComponents.HeaderMenuComponent;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CategoriesPage;
import pages.MainPage;

import static data.menu.HeaderMenuData.EDUCATION;

@ExtendWith(UIExtension.class)
public class CheckRandomCourseInEDUTest {

    @Inject
    private MainPage mainPage;
    @Inject
    private HeaderMenuComponent headerMenuComponent;
    @Inject
    private CategoriesPage categoriesPage;

    @Test
    public void checkRandomCourseInEDUTest() {
        mainPage.open();
        headerMenuComponent.setFocusToMenuItem(EDUCATION);
        categoriesPage.getTextRandomCategoryAndClick();
        categoriesPage.checkCurrentURLMatchingCategory();
    }
}

