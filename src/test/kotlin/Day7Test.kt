import kotlin.test.*

class Day7Test {

    @Test
    fun `passes example part a`() {
        val result = day7a("day7-example")
        assertEquals(4, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day7b("day7-example")
        assertEquals(32, result)
    }
}