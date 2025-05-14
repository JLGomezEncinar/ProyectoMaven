package iessanalberto.dam1.proyectomaven;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.CountDownLatch;

public abstract class JavaFXTestBase {

    private static boolean toolkitInitialized = false;

    @BeforeAll
    public static void initToolkit() throws Exception {
        if (!toolkitInitialized) {
            CountDownLatch latch = new CountDownLatch(1);

            new Thread(() -> {
                try {
                    Platform.startup(() -> {
                        // No hacer nada, solo inicializar
                    });
                } catch (IllegalStateException e) {
                    // Ya está inicializado, ignorar
                } finally {
                    latch.countDown();
                }
            }).start();

            latch.await(); // Espera a que el toolkit esté listo
            toolkitInitialized = true;
        }
    }
}
