package app.tasktrackersystems.tasktracker.common

import org.junit.Assert
import kotlin.test.Test
import app.tasktrackersystems.tasktracker.common.Utils.toTimeString

class UtilsTests {
    @Test
    fun oneSec() {
        Assert.assertEquals("1 second", 1L.toTimeString())
    }

    @Test
    fun under60sec() {
        Assert.assertEquals("59 seconds", 59L.toTimeString())
    }
    @Test
    fun sixtySec() {
        Assert.assertEquals("1 minute", 60L.toTimeString())
    }
    @Test
    fun sixtyOneSec() {
        Assert.assertEquals("1 minute 1 second", 61L.toTimeString())
    }
    @Test
    fun sixtyTwoSec() {
        Assert.assertEquals("1 minute 2 seconds", 62L.toTimeString())
    }
    @Test
    fun fiftyNineMinFiftyNineSec() {
        Assert.assertEquals("59 minutes 59 seconds", 3599L.toTimeString())
    }
    @Test
    fun oneHour() {
        Assert.assertEquals("1 hour", 3600L.toTimeString())
    }
    @Test
    fun oneHourOneSec() {
        Assert.assertEquals("1 hour 1 second", 3601L.toTimeString())
    }
    @Test
    fun oneHourTwoSec() {
        Assert.assertEquals("1 hour 2 seconds", 3602L.toTimeString())
    }
    @Test
    fun oneHour1minute1Sec() {
        Assert.assertEquals("1 hour 1 minute 1 second", 3661L.toTimeString())
    }
    @Test
    fun oneHour1minute2Sec() {
        Assert.assertEquals("1 hour 1 minute 2 seconds", 3662L.toTimeString())
    }
    @Test
    fun oneHour2minute1Sec() {
        Assert.assertEquals("1 hour 2 minutes 1 second", 3721L.toTimeString())
    }
    @Test
    fun oneHour2minute2Sec() {
        Assert.assertEquals("1 hour 2 minutes 2 seconds", 3722L.toTimeString())
    }

}