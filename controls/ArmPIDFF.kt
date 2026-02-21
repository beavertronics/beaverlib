package beaverlib.controls

import beaverlib.utils.Units.Angular.AngleUnit
import beaverlib.utils.Units.Angular.AngularVelocity
import beaverlib.utils.Units.Angular.radians
import beaverlib.utils.Units.Angular.radiansPerSecond
import edu.wpi.first.math.controller.ArmFeedforward
import edu.wpi.first.math.controller.PIDController

/** A combined Proportional Integral Derivative and an Arm Feed Forward controller. */
class ArmPIDFF(PIDConstants: PIDConstants, FFConstants: ArmFeedForwardConstants) {
    /** The Proportional Integral Derivative controller part of the PIDFF */
    val PID = PIDController(PIDConstants.P, PIDConstants.I, PIDConstants.D)
    /** The FeedForward part of the PIDFF */
    val FeedForward = ArmFeedforward(FFConstants.kS, FFConstants.kG, FFConstants.kV)

    /** The goal state for the PIDFF */
    var setpoint: AngleUnit
        get() = PID.setpoint.radians
        set(value) {
            PID.setpoint = value.asRadians
        }

    /**
     * Returns the calculated PID value given [measurement], plus the calculated FeedForwardValue
     * given the [setpoint]
     *
     * @param measurement The measured value of what the PIDFF controls
     */
    fun calculate(measurement: AngleUnit, velocity: AngularVelocity = 0.radiansPerSecond): Double {
        return PID.calculate(measurement.asRadians) +
            FeedForward.calculate(measurement.asRadians, velocity.asRadiansPerSecond)
    }

    /** Returns this PID [PIDController.atSetpoint] */
    fun atSetpoint(): Boolean {
        return PID.atSetpoint()
    }
}

/** @return A WPILib PIDController using the pid constants in the class */
fun PIDConstants.toArmPidFF(FFConstants: ArmFeedForwardConstants): ArmPIDFF {
    return ArmPIDFF(this, FFConstants)
}
