package pw.ast

import org.junit.Assert.*
import org.junit.Test

@Test
fun basicParse() {
    val pl = pipeline("test-pipeline")  {
        stage("build") {
            job("maven") {
                maven("testbed/SampleJavaService") {}
            }
        }
        stage("deploy") {
            job("maven") {
                maven("testbed/SampleJavaService") {}
            }
        }
    }
    assertEquals("test-pipeline", pl.name)

    val buildStage = pl.stageByName("build")
    assertEquals("build", buildStage.name)

    val job = buildStage.jobByName("build and test")
    assertEquals("maven", job.name)

    val deployStage = pl.stageByName("deploy")
    assertEquals("deploy", deployStage.name)

}
