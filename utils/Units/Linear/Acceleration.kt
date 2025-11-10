package beaverlib.utils.Units.Linear

import beaverlib.utils.Units.Time
import edu.wpi.first.units.AccelerationUnit
import edu.wpi.first.units.Unit

@JvmInline
value class Acceleration (val asMetersPerSecondSquared: Double) {
    operator fun plus(other: Acceleration) = Acceleration(asMetersPerSecondSquared + other.asMetersPerSecondSquared)
    operator fun minus(other: Acceleration) = Acceleration(asMetersPerSecondSquared - other.asMetersPerSecondSquared)
    operator fun times(factor: Double) = Acceleration(asMetersPerSecondSquared * factor)
    operator fun div(factor: Double) = Acceleration(asMetersPerSecondSquared / factor)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = Acceleration(-asMetersPerSecondSquared)
    operator fun compareTo(other: Acceleration) = asMetersPerSecondSquared.compareTo(other.asMetersPerSecondSquared)
    override fun toString() = "$asMetersPerSecondSquared m/s^2"
}

// Constructors
inline val Number.metersPerSecondSquared get() = Acceleration(this.toDouble())
inline val Number.`Mps^2` get() = this.metersPerSecondSquared

inline val Number.feetPerSecondSquared get() = Acceleration(this.toDouble() * 0.3048)

// Destructors
inline val Acceleration.asFeetPerSecondSquared get() = asMetersPerSecondSquared / 0.3048

val earthGravity = Acceleration(9.80665)