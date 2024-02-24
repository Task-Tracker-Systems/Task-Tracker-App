package app.tasktrackersystems.tasktracker.common

object Utils {
    fun Long.toTimeString(): String {
        var result = ""

        val h = this / 60 / 60
        var hour = "hours"
        if (h == 1L) {
            hour = "hour"
        }
        if(h > 0) {
            result = "$h $hour"
        }

        val m = (this / 60) % 60
        var min = "minutes"
        if (m == 1L) {
            min = "minute"
        }
        if(m > 0) {
            result += " $m $min"
        }

        val s = this % 60
        var sec = "seconds"
        if (s == 1L) {
            sec = "second"
        }
        if(s > 0L) {
            result += " $s $sec"
        }

        return result.trim()
    }
}