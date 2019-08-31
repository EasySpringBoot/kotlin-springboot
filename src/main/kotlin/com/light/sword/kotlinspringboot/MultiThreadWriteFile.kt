package com.light.sword.kotlinspringboot

import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.ConcurrentLinkedQueue

@Service
class MultiThreadWriteFile {

fun writeFile() {
    val queue = ConcurrentLinkedQueue<String>()

    val file = File("test.data")
    if (!file.exists()) {
        file.createNewFile()
    }
    val out = FileOutputStream(file)

    // 消费：单独启动一个线程来写文件
    Thread(Consumer(queue = queue, out = out)).start()

    // 生产：启动多个线程生产数据
    for (i in 0..1000) {
        Thread(Producer(queue = queue, element = "E$i")).start()
        Thread.sleep(10)
    }
}
}

/**
 * 将要写入文件的数据存入队列中
 */
class Producer(var queue: ConcurrentLinkedQueue<String>, var element: String) : Runnable {

    override fun run() {
        val item = "$element: ${Thread.currentThread().name}\n"
        queue.add(item)
        println("Product $item")
    }
}

/**
 * 从队列中消费数据
 */
class Consumer(var out: FileOutputStream, var queue: ConcurrentLinkedQueue<String>) : Runnable {

    override fun run() {
        while (true) {
            if (queue.isNotEmpty()) {
                Thread.sleep(10)
                val element = queue.poll()
                out.write(element.toByteArray())
                println("Consume $element")
            }
        }
    }
}

