package frc.robot.beaverlib.utils.Units.Electrical


@JvmInline
value class Energy (val asJoules: Double) {
    // Basic Math
    operator fun plus(other: Energy) = Energy(asJoules + other.asJoules)
    operator fun minus(other: Energy) = Energy(asJoules - other.asJoules)
    operator fun times(factor: Double) = Energy(asJoules * factor)
    operator fun div(factor: Double) = Energy(asJoules / factor)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = Energy(-asJoules)
    operator fun compareTo(other: Energy) = asJoules.compareTo(other.asJoules)
    override fun toString() = "$asJoules joules"
}

//Constructors
val Number.milliJoules get() = Energy(toDouble()/1000)
val Number.joules get() = Energy(toDouble())

/**
 * WARNING: IF YOU ARE USING THIS YOU ARE PROBABLY GOING TO BLOW SOMETHING UP
 * Why do we have this in the codebase? please remove it; we're trying to avoid spaghetti
 */
val Number.kiloJoules get() = Energy(toDouble()*1000)


//Destructors
val Energy.asMillijoules get() = asJoules*1000
val Energy.asKilojoules get() = asJoules/1000