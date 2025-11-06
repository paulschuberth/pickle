import org.junit.jupiter.api.DynamicNode
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ExampleTest {

    private val kukumber = Kukumber()

    private lateinit var today: String

    @TestFactory
    fun `Run kukumber tests`(): List<DynamicNode> {
        kukumber.feature(
            "Is it friday yet?", "Everyone wants to know when it's Friday"
        ) {
            scenario("Sunday isn't Friday") {

                given("Today is Sunday") {
                    today = "Sunday"
                }

                then("it is not Friday") {
                    assertFalse(isItFriday(today))
                }
            }

            scenario("Monday isn't Friday") {
                given("Today is Monday") {
                    today = "Monday"
                }

                then("it is not Friday") {
                    assertFalse(isItFriday(today))
                }
            }

            scenario("Friday is Friday") {
                given("Today is Friday") {
                    today = "Friday"
                }

                then("it is not Friday") {
                    assertTrue(isItFriday(today))
                }
            }
        }

        return kukumber.createTests()
    }


    private fun isItFriday(today: String): Boolean {
        return "Friday" == today
    }
}
