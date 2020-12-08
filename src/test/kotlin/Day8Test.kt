import kotlin.test.*

class Day8Test {

    @Test
    fun `passes example part a`() {
        val result = day8a("day8-example")
        assertEquals(5, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day8b("day8-example")
        assertEquals(8, result)
    }
}
