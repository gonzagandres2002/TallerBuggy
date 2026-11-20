package co.edu.udea.certificacion.buggy.rentacarros.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/** Paginas de autos: inicio (Popular Make), listado de modelos de la marca y detalle del modelo. */
public final class CarsPage {

    public static final Target LNK_POPULAR_MAKE = Target.the("popular make card")
            .locatedBy("//h2[normalize-space()='Popular Make']/following-sibling::a");

    /** Target parametrizado: {0} se reemplaza con el nombre del modelo (CarsPage.LNK_MODEL.of("Veneno")). */
    public static final Target LNK_MODEL = Target.the("model {0} in the list of models")
            .locatedBy("//table[contains(@class,'cars')]//td/a[normalize-space()='{0}']");

    // Localizado por ID
    public static final Target TXT_COMMENT = Target.the("comment field").located(By.id("comment"));

    public static final Target BTN_VOTE = Target.the("vote button")
            .locatedBy("//button[normalize-space()='Vote!']");

    /** Mensaje del bloque de votos: "Thank you for your vote!" o "You need to be logged in to vote." */
    public static final Target LBL_VOTE_MESSAGE = Target.the("vote message")
            .locatedBy("//div[contains(@class,'card-block')]/p[contains(@class,'card-text')]");

    /** Celda de la tabla de comentarios cuyo texto es exactamente {0}. */
    public static final Target CELL_COMMENT = Target.the("comment {0} in the comments table")
            .locatedBy("//table[contains(@class,'table')]//tbody/tr/td[3][normalize-space()='{0}']");

    private CarsPage() {
    }
}
