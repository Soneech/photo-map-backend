package org.soneech.photomap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PhotoMapApplication

fun main(args: Array<String>) {
	runApplication<PhotoMapApplication>(*args)
}
