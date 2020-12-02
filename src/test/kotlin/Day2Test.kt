import kotlin.test.*

class Day2Test {

    @Test
    fun `passes example part a`() {
        val result = day2a("day2-example")
        assertEquals(2, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day2b("day2-example")
        assertEquals(1, result)
    }
}