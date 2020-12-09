import kotlin.test.*

class Day9Test {

    @Test
    fun `passes example part a`() {
        val result = day9a("day9-example", 5)
        assertEquals(127, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day9b("day9-example", 5)
        assertEquals(62, result)
    }
}
