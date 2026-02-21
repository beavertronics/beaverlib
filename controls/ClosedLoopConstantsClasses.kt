package beaverlib.controls

import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.math.controller.SimpleMotorFeedforward

/**
 * @property kS (roughly) how much voltage to overcome static friction
 * @property kV How much voltage to maintain a velocity
 * @property kA How much voltage to accelerate (Can go unused)
 */
data class SimpleMotorFeedForwardConstants(val kS: Double, val kV: Double, val kA: Double)

/**
 * @property kS (roughly) how much voltage to overcome static friction
 * @property kG How much voltage to overcome gravity (can be referred to as kCos)
 * @property kV How much voltage to maintain a velocity
 * @property kA How much voltage to accelerate (Can go unused)
 */
data class ArmFeedForwardConstants(
    val kS: Double,
    val kG: Double,
    val kV: Double,
    val kA: Double = 0.0,
)

/**
 * @property p Proportional to the error. If the error is large, apply a large correction; if it's
 *   small, apply a small correction.
 * @property i Proportional to the integral (accumulated sum) of the error. If the error has been
 *   bad for a long time, increase correction. Often unstable; commonly kept low or at 0.
 * @property d Proportional to the derivative (rate of change) of the error. If the error is
 *   starting to get worse, correct preemptively; if it's improving quickly, reduce correction to
 *   avoid overshoot.
 */
data class PIDConstants(val p: Double, val i: Double, val d: Double)

/** @return A WPILib SimpleMotorFeedForward using the feedforward constants in the class */
fun SimpleMotorFeedForwardConstants.toFeedForward(): SimpleMotorFeedforward {
    return SimpleMotorFeedforward(this.kS, this.kV, this.kA)
}

/** @return A WPILib PIDController using the pid constants in the class */
fun PIDConstants.toPID(): PIDController {
    return PIDController(this.p, this.i, this.d)
}

val PIDConstants.PathPlannerPID: com.pathplanner.lib.config.PIDConstants
    get() = com.pathplanner.lib.config.PIDConstants(this.p, this.i, this.d)
