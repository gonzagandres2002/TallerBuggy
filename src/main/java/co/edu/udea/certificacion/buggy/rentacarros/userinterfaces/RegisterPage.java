package co.edu.udea.certificacion.buggy.rentacarros.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/** Pagina "Register with Buggy Cars Rating". */
public final class RegisterPage {

    // Localizados por ID
    public static final Target TXT_LOGIN = Target.the("login of the new account").located(By.id("username"));
    public static final Target TXT_FIRST_NAME = Target.the("first name").located(By.id("firstName"));
    public static final Target TXT_LAST_NAME = Target.the("last name").located(By.id("lastName"));
    public static final Target TXT_PASSWORD = Target.the("password of the new account").located(By.id("password"));
    public static final Target TXT_CONFIRM_PASSWORD = Target.the("password confirmation").located(By.id("confirmPassword"));

    // Localizados por XPATH
    public static final Target BTN_REGISTER = Target.the("register button")
            .locatedBy("//form//button[@type='submit' and normalize-space()='Register']");

    public static final Target LBL_SUCCESS = Target.the("registration success message")
            .locatedBy("//div[contains(@class,'result') and contains(@class,'alert-success')]");

    private RegisterPage() {
    }
}
