# Taller de automatización: Buggy Cars Rating

Calidad de Software 2026-2, Universidad de Antioquia.
Integrantes: Andrés González y Tomás Cadavid.

Pruebas E2E automatizadas con el patrón **Screenplay** sobre [Buggy Cars Rating](https://buggy.justtestit.org/).
El flujo principal hace **Sign Up → Sign In → seleccionar un auto → votar dejando un comentario → validar que el comentario quedó registrado**.

**Stack:** Java 17, Gradle, Serenity BDD 4.1.10, Cucumber (Gherkin) y Selenium WebDriver (Chrome).

## 1. Cómo se generó el arquetipo (scaffold)

La estructura no se creó a mano. Se generó con el plugin [ScreenplayArchitecture](https://github.com/jumosqu12/ScreenplayArchitecture):

```gradle
plugins { id "io.github.jumosqu12.screenplayarchitecture" version "2.0.1" }
```
```bash
gradle spa --projectName=TallerBuggy --groupId=co.edu.udea.certificacion.buggy --principalPackage=rentacarros --type=UX
gradle gpl --name=TallerBuggy --type=azure      # pipeline de CI/CD (TallerBuggy_Build.yaml)
```

Ajustes que se le hicieron al arquetipo generado:

| Problema del arquetipo | Ajuste |
|---|---|
| No incluye el plugin de reportes de Serenity, así que `aggregate` no existe | Se agregó `net.serenity-bdd.serenity-gradle-plugin` 4.1.10 |
| El `glue` del Runner apuntaba a `...buggy.stepdefinitions` (le faltaba `rentacarros`) | Se corrigió en el Runner |
| La URL de ejemplo era Google, como `swaglabsUrl` | Se cambió por `pages.buggyUrl` en `serenity.conf` |
| El pipeline usaba el pool privado y el SonarQube de Bancolombia, y omitía las pruebas | Se adaptó a un agente hospedado de Azure que corre las pruebas y publica el reporte |

## 2. Estructura (patrón Screenplay)

```
src/main/java/co/edu/udea/certificacion/buggy/rentacarros/
├── tasks/            Acciones de NEGOCIO: OpenTheBrowser, SignUp, SignIn, SelectTheCar, VoteForTheCar
├── interactions/     "La menuda": Authenticate, FillTheRegistrationForm, WriteTheComment, EnterSecretly
├── questions/        Validaciones: TheMessage (String), TheComment (Boolean)
├── userinterfaces/   Targets por ID, NAME y XPATH: NavigationBar, RegisterPage, CarsPage
├── models/           User (objeto de negocio que viaja entre capas)
├── exceptions/       CommentNotRegisteredError (falla funcional)
└── utils/            Wait (pausa para la sustentación), TestDataFactory (datos únicos), Constants
src/test/java/.../
├── runners/          RateCarRunner
└── stepdefinitions/  RateCarStepDefinitions (prepara el actor y define todos los pasos)
src/test/resources/
├── features/         rate_car/rate_car.feature  (Gherkin en inglés, sin tildes)
└── serenity.conf     navegador, URL, capturas y espera implícita
```

Así se lee la cadena: **Feature (Gherkin) → Runner → StepDefinition → Actor → Task (negocio) → Interaction (detalle) → Target (UI) → Question (validación)**.

## 3. Escenarios: un feature, tres casos

Todo está en `rate_car.feature`. El `Background` abre la página de Buggy antes de cada caso.

| Escenario | Qué hace | Tipo |
|---|---|---|
| E2E (Scenario Outline), ejemplo Veneno | Registro, login, seleccionar el auto, votar con comentario, validar el mensaje y el comentario | Camino feliz |
| E2E (Scenario Outline), ejemplo AVENTADOR | Lo mismo con otro auto, gracias a `Examples` | Camino feliz |
| Visitante sin sesión | Entra al auto sin iniciar sesión y el sitio le pide iniciar sesión para votar | Alterno |

## 4. Cómo ejecutar

Requisito: Java 17 y Chrome. No hace falta instalar Gradle, porque se usa el wrapper.

```bash
./gradlew clean test aggregate                                   # ejecuta todo y genera el reporte
./gradlew clean test aggregate -Ddemo.delay.ms=2500              # más lento, para la sustentación
./gradlew clean test aggregate -Dheadless.mode=true -Ddemo.delay.ms=0   # sin ventana (CI)
```

Desde IntelliJ también se puede: clic derecho sobre un Runner y luego *Run*.
Configuración: *Settings → Gradle → Gradle JVM = 17* y los plugins **Cucumber for Java** y **Gherkin**.

**Reporte de Serenity BDD:** se genera en `target/site/serenity/index.html`, con capturas de pantalla en cada paso.

## 5. Buenas prácticas aplicadas (para la sustentación)

- **Principio FIRST.** Cada caso es **independiente** y **repetible**: cada E2E crea su propio usuario único en tiempo de ejecución (`TestDataFactory`), así que no depende de otros escenarios ni de datos previos. Todos terminan en una **Question**, lo que cumple con ser *testeable*.
- **Seguridad:**
  - No hay credenciales reales ni contraseñas en el código ni en los `.feature`; se generan al vuelo.
  - La interacción `EnterSecretly` escribe las contraseñas sin que aparezcan en el reporte, donde se ven como `(******)`. `Enter.theValue` de Serenity sí las expondría.
  - `User.toString()` no imprime la contraseña.
- **Tasks en términos de negocio** (`SignUp`, `SignIn`, `VoteForTheCar`) y el detalle en las **Interactions**.
- **Localizadores con las 3 técnicas vistas en clase:** `By.id` (registro), `By.name` (login) y XPath (resto). Las URL van en `serenity.conf`, no en el código.
- **Questions de los dos estilos que mostró el profesor:** una que retorna `String`, que se compara con un Matcher en el StepDefinition, y otra que retorna `Boolean`, que compara dentro de la Question.
- **Memoria del actor** (`remember` / `recall`): el usuario registrado y el comentario publicado viajan entre pasos sin variables globales.
- **Comentario único** (marca `[run ...]`): permite validar sin ambigüedad que *ese* comentario quedó registrado.
- **Fallas vs. errores:** las excepciones propias (`orComplainWith`) explican la falla funcional en el reporte.
- **`Wait.aLittleBitTime()`** entre pasos, configurable con `-Ddemo.delay.ms`, para observar la prueba.
