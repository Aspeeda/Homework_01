package components;

import annotations.components.Component;
import common.AbsCommon;
import exceptions.NoStrategyFindComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AbsComponent<T> extends AbsCommon<T> {
    public AbsComponent(WebDriver driver) {
        super(driver);
    }

    protected By getComponentBy() {
        Component component = getClass().getAnnotation(Component.class);
        if (component != null) {
            Pattern pattern = Pattern.compile("(.*?):(.*?)");
            Matcher matcher = pattern.matcher(component.value());
            if (matcher.find()) {
                switch (matcher.group(1)) {
                    case "css": {
                        return By.cssSelector(matcher.group(2));
                    }
                    case "internal_component": {
                        return By.xpath(matcher.group(2));
                    }
                }
            }
        }
        throw new NoStrategyFindComponent();
    }

    protected WebElement getComponentEntity() {
        return $(getComponentBy());
    }


}
