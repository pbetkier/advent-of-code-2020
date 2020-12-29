import kotlin.test.*

class Day15Test {

    @Test
    fun `passes example part a`() {
        assertEquals(436, day15a("0,3,6"))
        assertEquals(1, day15a("1,3,2"))
        assertEquals(10, day15a("2,1,3"))
        assertEquals(27, day15a("1,2,3"))
        assertEquals(78, day15a("2,3,1"))
        assertEquals(438, day15a("3,2,1"))
        assertEquals(1836, day15a("3,1,2"))
    }

    @Test
    fun `passes example part b`() {
        assertEquals(175594, day15b("0,3,6"))
//        assertEquals(2578, day15b("1,3,2"))
//        assertEquals(3544142, day15b("2,1,3"))
//        assertEquals(261214, day15b("1,2,3"))
//        assertEquals(6895259, day15b("2,3,1"))
//        assertEquals(18, day15b("3,2,1"))
//        assertEquals(362, day15b("3,1,2"))
    }
}
