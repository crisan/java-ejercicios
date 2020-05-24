package org.example;

import static org.junit.Assert.assertTrue;


import com.sun.org.slf4j.internal.LoggerFactory;
import completablefuture.Sleep;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Unit test for simple App.
 */
public class CompletableFutureTest {

    private final static Logger LOGGER = Logger.getLogger(CompletableFutureTest.class.getName());

    private ExecutorService executor;

    @Before
    public void setUp() {
        executor = Executors.newFixedThreadPool(5);
    }

    @After
    public void shutDown() throws InterruptedException {

    }

    @Test
    public void testFutureCreationNewCompleted() {
        CompletableFuture<String> future = CompletableFuture.completedFuture("Prueba");
        LOGGER.info("Completable creado.");
        future.whenCompleteAsync((s, e) -> LOGGER.info("Result: " + s), executor);
    }

    @Test
    public void testFutureCreationNew() {
        CompletableFuture<String> future = new CompletableFuture<>();
        LOGGER.info("Completable creado.");
        future.whenCompleteAsync((s, e) -> LOGGER.info("Result: " + s), executor);
        LOGGER.info("Registrado el when complete...");
        Sleep.sleepSeconds(2);
        future.complete("Completado!");
    }

    @Test
    public void testFutureCreationRunAsync() {
        CompletableFuture<Void> futureAsync = CompletableFuture.runAsync(() -> {
            LOGGER.info("Comenzando runAsync...");
            Sleep.sleepSeconds(3);
            LOGGER.info("Terminado runAsync!");
        }, executor);

        futureAsync.whenCompleteAsync((s, e) -> LOGGER.info("Sin resultado..."), executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureCreationSupplyAsync() {
        CompletableFuture<String> futureAsync = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando supplyAsync...");
            Sleep.sleepSeconds(3);
            LOGGER.info("Terminado supplyAsync!");
            return "Terminado";
        }, executor);

        futureAsync.whenCompleteAsync((s, e) -> LOGGER.info("Resultado supplyAsync: " + s),
                executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureCreationSupplyAsyncBlocking() throws Exception {
        CompletableFuture<String> futureAsync = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando supplyAsyncBlocking...");
            Sleep.sleepSeconds(3);
            LOGGER.info("Terminado supplyAsyncBlocking!");
            return "Terminado";
        }, executor);

        LOGGER.info("Resultado bloqueando supplyAsync: " + futureAsync.get());
    }





}
