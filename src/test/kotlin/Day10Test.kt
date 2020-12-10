import kotlin.test.*

class Day10Test {

    @Test
    fun `passes example part a`() {
        val result = day10a("day10-example")
        assertEquals(220, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day10b("day10-example")
        assertEquals(19208, result)
    }
}
