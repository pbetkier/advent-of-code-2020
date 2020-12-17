import kotlin.test.*

class Day13Test {

    @Test
    fun `passes example part a`() {
        val result = day13a("day13-example")
        assertEquals(295, result)
    }

    @Test
    fun `passes example part b simple`() {
        val result = day13b("day13b-simple-example")
        assertEquals(3417, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day13b("day13-example")
        assertEquals(1068781, result)
    }
}
