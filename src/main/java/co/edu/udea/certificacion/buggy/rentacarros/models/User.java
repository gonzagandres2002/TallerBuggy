package co.edu.udea.certificacion.buggy.rentacarros.models;

/**
 * Objeto de negocio: el usuario de Buggy Cars Rating.
 * Viaja entre StepDefinitions, Tasks e Interactions en lugar de mandar parametros sueltos.
 */
public class User {

    private final String login;
    private final String firstName;
    private final String lastName;
    private final String password;

    public User(String login, String firstName, String lastName, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    /** Nunca se imprime la contrasena: el toString aparece en logs y reportes. */
    @Override
    public String toString() {
        return "User{login='" + login + "', firstName='" + firstName + "', lastName='" + lastName + "'}";
    }
}
