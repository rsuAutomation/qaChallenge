package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.*;

public class BasePage {

    private WebDriver driver;

    public BasePage(final WebDriver driver) {

        this.driver = driver;
        initElements();
    }

    public final void initElements() {

        final List<Field> fields = new ArrayList<>();
        Class currentPageObject = this.getClass();

        while (currentPageObject != BasePage.class) {
            fields.addAll(new ArrayList<>(Arrays.asList(currentPageObject.getDeclaredFields())));
            currentPageObject = currentPageObject.getSuperclass();
        }

        for (Field field : fields) {
            final Html fieldAnnotation = field.getAnnotation(Html.class);
            final boolean accessible = field.isAccessible();

            if (fieldAnnotation != null) {
                try {
                    field.setAccessible(true);
                    field.set(this, new HtmlElement(fieldAnnotation.searchBy(), fieldAnnotation.value()));
                    field.setAccessible(accessible);
                } catch (IllegalAccessException e) {
                    // Log or throw your exception here
                }
            }
        }
    }

    public HtmlElement updateElement(final HtmlElement element, final String... values) {
        return element.updateElement(values);
    }

    public WebElement findElement(final HtmlElement element) {
        return driver.findElement(element.getLocator());
    }

    public void click(final HtmlElement element) {
        findElement(element).click();
    }

    public String getText(final HtmlElement element) {
        return findElement(element).getText();
    }
}