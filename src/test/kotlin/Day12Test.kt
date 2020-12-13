import kotlin.test.*

class Day12Test {

    @Test
    fun `passes example part a`() {
        val result = day12a("day12-example")
        assertEquals(25, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day12b("day12-example")
        assertEquals(286, result)
    }
}
