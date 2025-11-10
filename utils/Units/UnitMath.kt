package frc.robot.beaverlib.utils.Units

import beaverlib.utils.Units.Angular.AngleUnit
import beaverlib.utils.Units.Angular.AngularAcceleration
import beaverlib.utils.Units.Angular.AngularVelocity
import beaverlib.utils.Units.Electrical.Current
import beaverlib.utils.Units.Electrical.Resistance
import beaverlib.utils.Units.Electrical.VoltageUnit
import beaverlib.utils.Units.Force
import beaverlib.utils.Units.Frequency
import beaverlib.utils.Units.Linear.Acceleration
import beaverlib.utils.Units.Linear.DistanceUnit
import beaverlib.utils.Units.Linear.VelocityUnit
import beaverlib.utils.Units.Time
import beaverlib.utils.Units.Weight
import frc.robot.beaverlib.utils.Units.Electrical.Energy
import frc.robot.beaverlib.utils.Units.Electrical.Power

// Angle Unit
operator fun AngleUnit.times(other : DistanceUnit) = DistanceUnit(asRadians * other.asMeters)
operator fun DistanceUnit.times(other : AngleUnit) = DistanceUnit(asMeters * other.asRadians)

operator fun AngleUnit.times(other : Frequency) = AngularVelocity(asRadians * other.asHertz)
operator fun Frequency.times(other : AngleUnit) = AngularVelocity(asHertz * other.asRadians)

operator fun AngleUnit.div(other : Time) = AngularVelocity(asRadians/other.asSeconds)
operator fun AngleUnit.div(other: AngleUnit) = asRadians / other.asRadians
operator fun AngleUnit.div(other: AngularVelocity) = Time(asRadians / other.asRadiansPerSecond)


// Angular Velocity
operator fun AngularVelocity.times(other: Time) = AngleUnit(asRadiansPerSecond * other.asSeconds)
operator fun Time.times(other: AngularVelocity) = AngleUnit(asSeconds * other.asRadiansPerSecond)

operator fun AngularVelocity.times(other: DistanceUnit) = VelocityUnit(asRadiansPerSecond * other.asMeters)
operator fun DistanceUnit.times(other: AngularVelocity) = VelocityUnit(asMeters * other.asRadiansPerSecond)

operator fun AngularVelocity.times(other: Frequency) = AngularAcceleration(asRadiansPerSecond * other.asHertz)
operator fun Frequency.times(other: AngularVelocity) = AngularAcceleration(asHertz * other.asRadiansPerSecond)

operator fun AngularVelocity.div(other: Time) = AngularAcceleration(asRadiansPerSecond / other.asSeconds)
operator fun AngularVelocity.div(other: AngleUnit) = Frequency(asRadiansPerSecond / other.asRadians)
operator fun AngularVelocity.div(other: AngularAcceleration) = Time(asRadiansPerSecond / other.asRadiansPerSecondSquared)
operator fun AngularVelocity.div(other: AngularVelocity) = asRadiansPerSecond / other.asRadiansPerSecond
operator fun AngularVelocity.div(other: Frequency) = AngleUnit(asRadiansPerSecond / other.asHertz)


// Angular Acceleration
operator fun AngularAcceleration.times(other: Time) = AngularVelocity(asRadiansPerSecondSquared * other.asSeconds)
operator fun Time.times(other: AngularAcceleration) = AngularVelocity(asSeconds * other.asRadiansPerSecondSquared)

operator fun AngularAcceleration.times(other: DistanceUnit) = Acceleration(asRadiansPerSecondSquared * other.asMeters)
operator fun DistanceUnit.times(other: AngularAcceleration) = Acceleration(asMeters * other.asRadiansPerSecondSquared)

operator fun AngularAcceleration.div(other: Frequency) = AngularVelocity(asRadiansPerSecondSquared / other.asHertz)
operator fun AngularAcceleration.div(other: AngularVelocity) = Frequency(asRadiansPerSecondSquared * other.asRadiansPerSecond)
operator fun AngularAcceleration.div(other: AngularAcceleration) = asRadiansPerSecondSquared * other.asRadiansPerSecondSquared



// Distance Unit
operator fun DistanceUnit.times(factor: Frequency) = VelocityUnit(asMeters*factor.asHertz)
operator fun Frequency.times(factor: DistanceUnit) = VelocityUnit(asHertz*factor.asMeters)

operator fun DistanceUnit.div(factor: Time) = VelocityUnit(asMeters/factor.asSeconds)
operator fun DistanceUnit.div(factor: VelocityUnit) = Time(asMeters/factor.asMetersPerSecond)
operator fun DistanceUnit.div(factor: DistanceUnit) = asMeters/factor.asMeters



// Velocity Unit
operator fun VelocityUnit.times(factor: Frequency) = Acceleration(asMetersPerSecond * factor.asHertz)
operator fun Frequency.times(factor: VelocityUnit) = Acceleration(asHertz * factor.asMetersPerSecond)

operator fun VelocityUnit.times(factor: Time) = DistanceUnit(asMetersPerSecond * factor.asSeconds)
operator fun Time.times(factor: VelocityUnit) = DistanceUnit(asSeconds * factor.asMetersPerSecond)

operator fun VelocityUnit.div(factor: DistanceUnit) = Frequency(asMetersPerSecond / factor.asMeters)
operator fun VelocityUnit.div(factor: Time) = Acceleration(asMetersPerSecond / factor.asSeconds)
operator fun VelocityUnit.div(factor: Frequency) = Acceleration(asMetersPerSecond / factor.asHertz)
operator fun VelocityUnit.div(factor: VelocityUnit) = asMetersPerSecond / factor.asMetersPerSecond

// Acceleration Unit
operator fun Acceleration.times(factor: Time) = VelocityUnit(asMetersPerSecondSquared * factor.asSeconds)
operator fun Time.times(factor: Acceleration) = VelocityUnit(asSeconds * factor.asMetersPerSecondSquared)

operator fun Acceleration.times(factor: Weight) = Force(asMetersPerSecondSquared * factor.asKilograms)
operator fun Weight.times(factor: Acceleration) = Force(asKilograms * factor.asMetersPerSecondSquared)

operator fun Acceleration.div(factor: Frequency) = VelocityUnit(asMetersPerSecondSquared / factor.asHertz)
operator fun Acceleration.div(factor: VelocityUnit) = Frequency(asMetersPerSecondSquared / factor.asMetersPerSecond)
operator fun Acceleration.div(factor: Acceleration) = asMetersPerSecondSquared / factor.asMetersPerSecondSquared

// Force
operator fun Force.times(other: DistanceUnit) = Energy(asNewtons * other.asMeters)
operator fun DistanceUnit.times(other: Force) = Energy(asMeters * other.asNewtons)

operator fun Force.div(other: Weight) = Acceleration(asNewtons / other.asKilograms)
operator fun Force.div(other: Acceleration) = Weight(asNewtons / other.asMetersPerSecondSquared)
operator fun Force.div(other: Force) = asNewtons / other.asNewtons


// Electrical
operator fun VoltageUnit.times(other: Current) = Energy(asVolts * other.asAmps)
operator fun Current.times(other: VoltageUnit) = Energy(asAmps * other.asVolts)

operator fun Current.times(other : Resistance) = VoltageUnit(asAmps * other.asOhms)
operator fun Resistance.times(other : Current) = VoltageUnit(asOhms * other.asAmps)

operator fun VoltageUnit.div(other: Resistance) = Current(asVolts / other.asOhms)
operator fun VoltageUnit.div(other: Current) = Resistance(asVolts / other.asAmps)

operator fun VoltageUnit.div(other: VoltageUnit) = asVolts / other.asVolts
operator fun Current.div(other: Current) = asAmps / other.asAmps
operator fun Resistance.div(other: Resistance) = asOhms / other.asOhms


// Energy
operator fun Energy.times(other : Frequency) = Power(asJoules * other.asHertz)
operator fun Frequency.times(other : Energy) = Power(asHertz * other.asJoules)

operator fun Energy.div(other : Time) = Power(asJoules / other.asSeconds)
operator fun Energy.div(other : Energy) = asJoules / other.asJoules

// Power
operator fun Power.times(other : Time) = Energy(asWatts * other.asSeconds)
operator fun Time.times(other : Power) = Energy(asSeconds * other.asWatts)

operator fun Power.div(other : Frequency) = Energy(asWatts / other.asHertz)
operator fun Power.div(other : Power) = asWatts / other.asWatts
