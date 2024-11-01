package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.staticComponents.HeaderMenuComponent;
import components.staticComponents.LessonCardComponent;
import factories.WebDriverFactory;
import listeners.HighlightElementListener;
import org.openqa.selenium.WebDriver;
import pages.CatalogCoursesPage;
import pages.CategoriesPage;
import pages.LessonCardPage;
import pages.MainPage;

public class GuiceModules extends AbstractModule {

    public final WebDriver driver = new WebDriverFactory().create();

    @Provides
    public WebDriver getDriver() {
        return driver;
    }

    @Singleton
    @Provides
    public MainPage getMainPage() {
        return new MainPage(driver);
    }

    @Singleton
    @Provides
    public CatalogCoursesPage getCatalogCoursesPage(){return new CatalogCoursesPage(driver);}

    @Singleton
    @Provides
    public LessonCardPage getLessonCardPage() {
        return new LessonCardPage(driver);
    }

    @Singleton
    @Provides
    public HeaderMenuComponent getHeaderMenuComponent() {
        return new HeaderMenuComponent(driver);
    }

    @Singleton
    @Provides
    public CategoriesPage getCategoriesPage() {
        return new CategoriesPage(driver);
    }

    @Singleton
    @Provides
    public LessonCardComponent getLessonCardComponent() {
        return new LessonCardComponent(driver);
    }

    @Provides
    public HighlightElementListener getHighlightElementListener() {
        return new HighlightElementListener(driver);
    }
}
