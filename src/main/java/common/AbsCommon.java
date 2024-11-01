package common;

import listeners.HighlightElementListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringDecorator;
import waiters.Waiters;

public abstract class AbsCommon<T> {
    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;

    protected HighlightElementListener highlightElementListener;


    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiters = new Waiters(driver);
        this.highlightElementListener = new HighlightElementListener(driver);
        PageFactory.initElements(driver, this);
        initializeDriverWithListener();
    }

    protected WebElement $(By locator) {
        return driver.findElement(locator);
    }

    private void initializeDriverWithListener() {
        this.driver = new EventFiringDecorator<>(highlightElementListener).decorate(driver);
    }
}
