import kotlin.test.*

class Day6Test {

    @Test
    fun `passes example part a`() {
        val result = day6a("day6-example")
        assertEquals(11, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day6b("day6-example")
        assertEquals(6, result)
    }
}