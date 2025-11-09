import kotlin.test.assertEquals

class ExampleTest : PickleJar() {

    private lateinit var today: String

    init {
        pickle.feature(
            "Is it friday yet?", "Everyone wants to know when it's Friday"
        ) {
            val weekdays = listOf(
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
            )
            weekdays.filterNot { it == "Friday" }.forEach { weekday ->
                scenario("$weekday is not Friday") {
                    Given("Today is $weekday") {
                        today = weekday
                    }
                    var result: Boolean? = null
                    When("it is checked if today is Friday") {
                        result = isItFriday(today)
                    }
                    Then("the result is false") {
                        assertEquals<Boolean?>(false, result)
                    }
                }
            }
            weekdays.filter { it == "Friday" }.forEach { weekday ->
                scenario("$weekday is Friday") {
                    Given("Today is $weekday") {
                        today = weekday
                    }
                    var result: Boolean? = null
                    When("it is checked if today is Friday") {
                        result = isItFriday(today)
                    }
                    Then("the result is false") {
                        assertEquals<Boolean?>(true, result)
                    }
                }
            }
        }

    }

    private fun isItFriday(today: String): Boolean {
        return "Friday" == today
    }
}
