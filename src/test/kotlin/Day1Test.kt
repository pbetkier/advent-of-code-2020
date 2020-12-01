import kotlin.test.*

class Day1Test {

    @Test
    fun `passes example`() {
        val result = day1a(loadInts("day1a-example"))
        assertEquals(514579, result)
    }
}