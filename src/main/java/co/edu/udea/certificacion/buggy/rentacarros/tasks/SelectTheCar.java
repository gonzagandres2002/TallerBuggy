package co.edu.udea.certificacion.buggy.rentacarros.tasks;

import co.edu.udea.certificacion.buggy.rentacarros.utils.Wait;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.CarsPage.LNK_MODEL;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.CarsPage.LNK_POPULAR_MAKE;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.NavigationBar.LNK_HOME;

/** Tarea de negocio: seleccionar un modelo de la marca mas popular. */
public class SelectTheCar implements Task {

    private final String model;

    public SelectTheCar(String model) {
        this.model = model;
    }

    @Override
    @Step("{0} selects the model #model of the popular make")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(LNK_HOME));
        Wait.aLittleBitTime();
        actor.attemptsTo(Click.on(LNK_POPULAR_MAKE));
        Wait.aLittleBitTime();
        actor.attemptsTo(Click.on(LNK_MODEL.of(model)));
        Wait.aLittleBitTime();
    }

    public static SelectTheCar fromThePopularMake(String model) {
        return Tasks.instrumented(SelectTheCar.class, model);
    }
}
