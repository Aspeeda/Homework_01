package listeners;

import com.google.inject.Inject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;


    public class HighlightElementListener implements WebDriverListener {

        @Inject
        private WebDriver driver;

        public HighlightElementListener(WebDriver driver) {super();
        }

        @Override
        public void beforeClick(WebElement element) {
            highlightElement(element);
        }

        private void highlightElement(WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            js.executeScript("arguments[0].style.border=''", element);
        }
    }

