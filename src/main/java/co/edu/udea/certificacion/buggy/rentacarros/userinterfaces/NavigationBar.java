package co.edu.udea.certificacion.buggy.rentacarros.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/** Barra superior: formulario de login y enlaces de registro e inicio. */
public final class NavigationBar {

    // Localizado por NAME
    public static final Target TXT_LOGIN = Target.the("login field")
            .located(By.name("login"));

    // Localizado por XPATH (el registro tambien tiene un input name="password", por eso se acota al form-inline)
    public static final Target TXT_PASSWORD = Target.the("password field of the login bar")
            .locatedBy("//form[contains(@class,'form-inline')]//input[@name='password']");

    public static final Target BTN_LOGIN = Target.the("login button")
            .locatedBy("//form[contains(@class,'form-inline')]//button[normalize-space()='Login']");

    public static final Target LNK_REGISTER = Target.the("register link")
            .locatedBy("//a[contains(@class,'btn-success-outline') and normalize-space()='Register']");

    public static final Target LNK_HOME = Target.the("Buggy Rating home link")
            .locatedBy("//a[contains(@class,'navbar-brand')]");

    private NavigationBar() {
    }
}
