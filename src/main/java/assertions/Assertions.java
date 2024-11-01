package assertions;

import org.openqa.selenium.WebDriver;
import pages.AbsBasePage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Assertions<T> extends AbsBasePage<Assertions> {


    public Assertions(WebDriver driver) {
        super(driver);
    }



}
