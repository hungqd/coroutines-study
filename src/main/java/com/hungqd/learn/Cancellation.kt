package com.hungqd.learn

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        try {
            repeat(10) {
                println("[Job] I'm sleeping $it...")
                delay(500)
            }
        } catch (e: Throwable) {
            println("[Job] I'm caught exception: $e")
        } finally {
            withContext(NonCancellable) {
                println("[Job] I'm running finally")
                delay(1000L)
                println("[Job] And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(2000)
    println("[Main] I'm tired of waiting!")
    job.cancelAndJoin()
    println("[Main] Now I can quit.")
}