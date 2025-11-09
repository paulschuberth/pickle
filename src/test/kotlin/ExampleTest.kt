import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ExampleTest : PickleJar() {

    private lateinit var today: String

    init {
        pickle.feature(
            "Is it friday yet?", "Everyone wants to know when it's Friday"
        ) {
            scenario("Sunday isn't Friday") {

                Given("Today is Sunday") {
                    today = "Sunday"
                }

                Then("it is not Friday") {
                    assertFalse(isItFriday(today))
                }
            }

            scenario("Monday isn't Friday") {
                Given("Today is Monday") {
                    today = "Monday"
                }

                Then("it is not Friday") {
                    assertFalse(isItFriday(today))
                }
            }

            scenario("Friday is Friday") {
                Given("Today is Friday") {
                    today = "Friday"
                }

                Then("it is not Friday") {
                    assertTrue(isItFriday(today))
                }
            }
        }


        pickle.feature(
            "Feature descriptions are presented", """
            The description of a feature can provide more information. I may even
            go across multiple lines.
            """.trimIndent()
        ) {
            scenario("The feature description is set") {
                Given("a feature with a description")
                When("the test is executed")
                Then("the description is logged to stdout")
            }
        }


    }

    private fun isItFriday(today: String): Boolean {
        return "Friday" == today
    }
}
