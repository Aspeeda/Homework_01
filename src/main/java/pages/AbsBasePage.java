package pages;

import annotations.Path;
import annotations.PathTemplate;
import common.AbsCommon;
import exceptions.PathNotValid;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class AbsBasePage<T> extends AbsCommon<T> {

    private final String BASE_URL = System.getProperty("baseUrl");

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "h1")
    private WebElement header;

    private String getPath() {
        Path path = getClass().getAnnotation(Path.class);
        if (path == null) {
            throw new PathNotValid();
        }
        return path.value().startsWith("/") ? path.value() : "/" + path.value();
    }

    public T open() {
        driver.manage().window().maximize();
        driver.get(BASE_URL + getPath());
        waiters.waitForCondition(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'")); //не придумала ничего лучше, один тест не проходит при стратегии EAGER
        return (T) this;
    }

    public T open(String... data) {
        driver.manage().window().maximize();
        PathTemplate pathTemplate = getClass().getAnnotation(PathTemplate.class);
        if (pathTemplate == null) {
            throw new PathNotValid();
        }
        String path = pathTemplate.value();
        for (int i = 0; i < data.length; i++) {
            path = path.replace("$" + i, data[i]);
        }
        driver.get(BASE_URL + path);
        return (T) this;
    }

    public T pageHeaderShouldBeSameAs(String title) {
        assertThat(header.getText()).as("Header of page should be {}", title).isEqualTo(title);
        return (T) this;
    }
}
