package components.staticComponents;

import components.AbsComponent;
import org.openqa.selenium.WebDriver;

public abstract class AbsStaticComponents<T> extends AbsComponent<T> {

    public AbsStaticComponents(WebDriver driver) {
        super(driver);
    }

    {
        try {
            waiters.waitForVisible(getComponentEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
