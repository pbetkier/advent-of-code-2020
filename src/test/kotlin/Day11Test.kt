import kotlin.test.*

class Day11Test {

    @Test
    fun `passes example part a`() {
        val result = day11a("day11-example")
        assertEquals(37, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day11b("day11-example")
        assertEquals(26, result)
    }
}
