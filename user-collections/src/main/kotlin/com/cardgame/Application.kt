package com.cardgame

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = ["com.cardgame"])
open class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
