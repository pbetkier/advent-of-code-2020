import kotlin.test.*

class Day14Test {

    @Test
    fun `passes example part a`() {
        val result = day14a("day14-example")
        assertEquals(165, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day14b("day14b-example")
        assertEquals(208, result)
    }
}
