package pw

import pw.ast.*
import pw.experimental.*

fun main(args: Array<String>) {

    val pl = pipeline("test-pipeline")  {
        stage("build") {
            step("first step name") {}
            betterStep("test-name") {}
        }
    }
    println(pl)


}

