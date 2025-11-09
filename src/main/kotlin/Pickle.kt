import org.junit.jupiter.api.DynamicContainer
import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest

class Pickle {

    private val features: MutableList<Feature> = mutableListOf()

    fun feature(name: String, description: String, scenarios: Feature.() -> Unit): Unit {
        val feature = Feature(name, description)
        feature.scenarios()
        features.add(feature)
    }

    fun createTests(): List<DynamicContainer> {
        return featureNodes()
    }

    private fun featureNodes(): List<DynamicContainer> {
        return features.map {
            val displayName = "Feature: ${it.featureName}"
            dynamicContainer(displayName, it.scenarioNodes())
        }
    }
}


class Feature(
    val featureName: String,
    val description: String,
) {
    private val scenarios: MutableList<Scenario> = mutableListOf()

    fun scenario(name: String, steps: Scenario.() -> Unit): Unit {
        val scenario = Scenario(name)
        scenario.steps()
        scenarios.add(scenario)
    }

    fun scenarioNodes(): List<DynamicContainer> {
        return scenarios.map { scenario ->
            dynamicContainer(
                "Scenario: ${scenario.scenarioName}",
                listOf(scenario.executeScenario())
            )
        }
    }
}

class Scenario(val scenarioName: String) {

    private val steps: MutableList<Step> = mutableListOf()

    @Suppress("FunctionName")
    fun Given(givenName: String, executable: () -> Unit = {}) {
        step("Given $givenName") { executable() }
    }

    @Suppress("FunctionName")
    fun When(whenName: String, executable: () -> Unit = {}) {
        step("When $whenName") { executable() }
    }

    @Suppress("FunctionName")
    fun Then(thenName: String, executable: () -> Unit = {}) {
        step("then $thenName") { executable() }
    }

    private fun step(stepName: String, executable: () -> Unit) {
        steps.add(Step(stepName, executable))
    }

    fun executeScenario(): DynamicTest {
        val testName = steps.joinToString { step -> step.stepName }
        return dynamicTest(testName) {
            steps.forEach { it.stepExecutable.invoke() }
        }
    }
}

open class Step(
    val stepName: String,
    val stepExecutable: () -> Unit,
)
