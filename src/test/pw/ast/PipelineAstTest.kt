package pw.ast

import org.junit.Assert.assertEquals
import org.junit.Test
import pw.step.maven

class PipelineAstTest {
    @Test
    fun basicParse() {
        val pl = pipeline("test-pipeline") {
            stage("build") {
                job("build-and-unit-test") {
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

        val job = buildStage.findJobByName("build-and-unit-test")
        assertEquals("build-and-unit-test", job.name)

        val deployStage = pl.stageByName("deploy")
        assertEquals("deploy", deployStage.name)
    }
}
