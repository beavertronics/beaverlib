package frc.robot.beaverlib.utils.Units.Electrical


@JvmInline
value class Power (val asWatts: Double) {
    // Basic Math
    operator fun plus(other: Power) = Energy(asWatts + other.asWatts)
    operator fun minus(other: Power) = Energy(asWatts - other.asWatts)
    operator fun times(factor: Double) = Energy(asWatts * factor)
    operator fun div(factor: Double) = Energy(asWatts / factor)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = Energy(-asWatts)
    operator fun compareTo(other: Energy) = asWatts.compareTo(other.asWatts)
    override fun toString() = "$asWatts joules"
}

//Constructors
val Number.milliWatts get() = Energy(toDouble()/1000)
val Number.watts get() = Energy(toDouble())

/**
 * WARNING: IF YOU ARE USING THIS YOU ARE PROBABLY GOING TO BLOW SOMETHING UP
 * Why do we have this in the codebase? please remove it; we're trying to avoid spaghetti
 */
val Number.kilowatts get() = Energy(toDouble()*1000)


//Destructors
val Energy.asMilliwatts get() = asWatts*1000
val Energy.asKilowatts get() = asWatts/1000