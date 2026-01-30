package com.vivim.deepmine.persistence;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class StorageExecutor {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, executor);
    }

    public void shutdownAndAwait(long timeout, TimeUnit unit) throws InterruptedException {
        executor.shutdown();
        if (!executor.awaitTermination(timeout, unit)) {
            executor.shutdownNow();
            executor.awaitTermination(timeout, unit);
        }
    }
}