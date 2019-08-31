package com.light.sword.kotlinspringboot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WriteFileController {
    @Autowired
    lateinit var writeFile: MultiThreadWriteFile

    @GetMapping(value = ["/writeFile"])
    fun writeFile(): String {
        writeFile.writeFile()
        return "writeFile"
    }
}