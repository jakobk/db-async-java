package com.jakobk.async.db.postgresql;

import scala.compat.java8.FutureConverters;
import scala.concurrent.Future;

import java.util.concurrent.CompletableFuture;

/**
 *
 */
class ScalaUtils {

    static <T> CompletableFuture<T> toCompletableFuture(Future<T> scalaFuture) {
        CompletableFuture<T> future = new CompletableFuture<>();
        FutureConverters.toJava(scalaFuture)
                .whenComplete((returnValue, throwable) -> {
                    if (returnValue != null) {
                        future.complete(returnValue);
                    } else {
                        future.completeExceptionally(throwable);
                    }
                });
        return future;
    }

    static <T> CompletableFuture<T> toCompletableFutureWithReturnValue(Future<?> scalaFuture, T returnValue) {
        return toCompletableFuture(scalaFuture).thenApply(unused -> returnValue);
    }

}
