package pages;

import annotations.PathTemplate;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@PathTemplate("categories/$1")
public class CategoriesPage extends AbsBasePage<CategoriesPage> {
    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@href, '/categories')]")
    private List<WebElement> categories;

    public String categoryName, categoryHref;

    public void getTextRandomCategoryAndClick() {
        int randomIndex = RandomUtils.nextInt(0, categories.size());
        WebElement selectedCategory = categories.get(randomIndex);
        categoryName = selectedCategory.getText();
        categoryHref = selectedCategory.getAttribute("href");
        selectedCategory.click();
    }

    public void checkCurrentURLMatchingCategory() {
        assertThat(driver.getCurrentUrl())
                .as("Current URL contains right category").isEqualTo(categoryHref + "/");
    }
}
