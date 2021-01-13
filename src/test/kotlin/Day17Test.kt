import kotlin.test.*

class Day17Test {

    @Test
    fun `passes example part a`() {
        val result = day17a("day17-example")
        assertEquals(112, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day17b("day17-example")
        assertEquals(848, result)
    }
}
