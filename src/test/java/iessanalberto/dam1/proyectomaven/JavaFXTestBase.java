package iessanalberto.dam1.proyectomaven;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

public abstract class JavaFXTestBase {

    private static boolean toolkitInitialized = false;

    @BeforeAll
    public static void initToolkit() {
        if (System.getenv("CI") != null) {
            // Si estamos en GitHub Actions, omitir inicialización de JavaFX
            return;
        }
        if (!toolkitInitialized) {
            if (!Platform.isFxApplicationThread()) {
                Platform.startup(() -> {
                    toolkitInitialized = true;
                });
            } else {
                toolkitInitialized = true;
            }
        }
    }

    @AfterAll
    public static void tearDown() {
        if (toolkitInitialized && Platform.isFxApplicationThread()) {
            Platform.exit();  // Cierra el Toolkit de JavaFX si es necesario
        }
    }
}
