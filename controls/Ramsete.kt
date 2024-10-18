package beaverlib.controls
// File adapted from 2898's bpsrobotics engine

import edu.wpi.first.math.controller.RamseteController
import edu.wpi.first.math.controller.SimpleMotorFeedforward
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds
import edu.wpi.first.math.trajectory.Trajectory
import edu.wpi.first.units.Distance
import beaverlib.odometry.PoseProvider
import beaverlib.utils.*
import beaverlib.utils.Units.Electrical.VoltageUnit
import beaverlib.utils.Units.Electrical.volts
import beaverlib.utils.Units.Linear.DistanceUnit
import beaverlib.utils.Units.Time

/**
 * @param trackWidth The width of the drivetrain, wheel-to-wheel
 * @param pose The pose provider used to determine the robot's position and orientation
 * @param leftController The PID (or something else) controller for the left side
 * @param rightController The PID (or something else) controller for the right side
 * @param leftFF The feedforward object for the left side
 * @param rightFF The feedforward object for the right side
 * @param b Increasing b makes it stick to the path more aggressively, defaults should work for most drivetrains
 * @param z Dampens the path sticking, defaults should work for most drivetrains
 */
class Ramsete(
    private val trackWidth: DistanceUnit,
    private val pose: PoseProvider,
    private val leftController: Controller,
    private val rightController: Controller,
    private val leftFF: SimpleMotorFeedforward,
    private val rightFF: SimpleMotorFeedforward,
    b: Double = 2.0, // TODO: Figure out how these two constants affect robot performance
    z: Double = 0.7,
) {
    private val ramsete = RamseteController(b, z)
    private val kinematics = DifferentialDriveKinematics(trackWidth.asMeters)

    private fun velocities(trajectory: Trajectory, time: Time): DifferentialDriveWheelSpeeds {
        val goal = trajectory.sample(time.asSeconds)
        val adjustedSpeeds = ramsete.calculate(pose.pose, goal)
        return kinematics.toWheelSpeeds(adjustedSpeeds)
    }

    data class WheelVoltages(val left: VoltageUnit, val right: VoltageUnit)

    fun voltages(trajectory: Trajectory, time: Time, wheelVelocities: DifferentialDriveWheelSpeeds): WheelVoltages {
        val p = pose.pose

        val goal = trajectory.sample(time.asSeconds)
        val adjustedSpeeds = ramsete.calculate(p, goal)
        val velocities = kinematics.toWheelSpeeds(adjustedSpeeds)

        val leftFeedforward = leftFF.calculate(velocities.leftMetersPerSecond).volts
        val rightFeedforward = rightFF.calculate(velocities.rightMetersPerSecond).volts

        return WheelVoltages(
            leftController.calculate(wheelVelocities.leftMetersPerSecond, velocities.leftMetersPerSecond).volts + leftFeedforward,
            rightController.calculate(wheelVelocities.rightMetersPerSecond, velocities.rightMetersPerSecond).volts + rightFeedforward
        )
    }
}