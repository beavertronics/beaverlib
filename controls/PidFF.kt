package beaverlib.controls

import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.math.controller.SimpleMotorFeedforward
import edu.wpi.first.util.sendable.Sendable
import edu.wpi.first.util.sendable.SendableBuilder

/** A combined Proportional Integral Derivative and Feed Forward controller. */
class PidFF(pidConstants: PIDConstants, ffConstants: SimpleMotorFeedForwardConstants) : Sendable {
    /** The Proportional Integral Derivative controller part of the PidFF */
    val pid = PIDController(pidConstants.p, pidConstants.i, pidConstants.d)
    /** The FeedForward part of the PidFF */
    val feedforward = SimpleMotorFeedforward(ffConstants.kS, ffConstants.kV, ffConstants.kA)

    /** The goal state for the PidFF */
    var setpoint: Double
        get() = pid.setpoint
        set(value) {
            pid.setpoint = value
        }

    /**
     * Returns the calculated PID value given [measurement], plus the calculated FeedForwardValue
     * given the [setpoint]
     *
     * @param measurement The measured value of what the PidFF controls
     */
    fun calculate(measurement: Double): Double {
        return pid.calculate(measurement) + feedforward.calculate(setpoint)
    }

    /** Returns this PID [PIDController.atSetpoint] */
    fun atSetpoint(): Boolean {
        return pid.atSetpoint()
    }

    override fun initSendable(builder: SendableBuilder) {
        builder.addDoubleProperty("kS", feedforward::getKs, feedforward::setKs)
        builder.addDoubleProperty("kV", feedforward::getKv, feedforward::setKv)
        builder.addDoubleProperty("kA", feedforward::getKa, feedforward::setKa)
        builder.addDoubleProperty("kP", pid::getP, pid::setP)
        builder.addDoubleProperty("kI", pid::getI, pid::setI)
        builder.addDoubleProperty("kD", pid::getD, pid::setD)
        builder.addDoubleProperty("setpoint", { setpoint }, { setpoint = it })
    }
}
