import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicNode
import org.junit.jupiter.api.TestFactory

open class PickleJar {
    protected val pickle = Pickle()

    @TestFactory
    fun runTests(): List<DynamicNode> {
        return pickle.createTests()
    }
}
