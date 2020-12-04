import kotlin.test.*

class Day4Test {

    @Test
    fun `passes example part a`() {
        val result = day4a("day4-example")
        assertEquals(2, result)
    }

    @Test
    fun `passes example part b`() {
        val result = day4b("day4b-valid-example")
        assertEquals(4, result)
    }
}