import kotlin.test.*

class Day16Test {

    @Test
    fun `passes example part a`() {
        val result = day16a("day16-example")
        assertEquals(71, result)
    }

    @ExperimentalStdlibApi
    @Test
    fun `passes example part b`() {
        val result = day16b("day16b-example")
        assertEquals(11 * 13, result)
    }
}
