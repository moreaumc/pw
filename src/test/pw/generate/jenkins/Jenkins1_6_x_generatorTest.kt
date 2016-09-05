package pw.generate.jenkins

import org.junit.Assert.*
import org.junit.Test
import pw.ast.pipeline
import pw.experimental.betterStep


class Jenkins1_6_x_generatorTest {

    @Test
    fun firstTest() {
        val pl = pipeline("test-pipeline")  {
            stage("build") {
                step("first step name") {}
            }
        }
        assertEquals("test-pipeline", pl.name)
    }

}

