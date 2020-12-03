import kotlin.test.*

class Day3Test {

    @Test
    fun `passes example part a`() {
        val result = day3a("day3-example")
        assertEquals(7, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day3b("day3-example")
        assertEquals(336, result)
    }
}