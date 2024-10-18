package beaverlib.controls

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.revrobotics.CANSparkLowLevel
import com.revrobotics.CANSparkLowLevel.MotorType
import com.revrobotics.CANSparkMax
import beaverlib.utils.Units.Electrical.VoltageUnit

interface BeaverMotorController {
    /** The percent output of the motor (-1.0.. 1.0 */
    fun get() : Double

    /** Runs the motor connected to the controller at the voltage "volts"
     *  (WARNING: May be unpredictable with non SparkMax controllers.)
     *  @param volts Voltage to run the motor at */
    fun setVoltage(volts : VoltageUnit)
}

class BeaverSparkMax(ID : Int, MotorType: CANSparkLowLevel.MotorType) : BeaverMotorController {
    var motorController : CANSparkMax = CANSparkMax(ID, MotorType)
    override fun get() = motorController.get()
    override fun setVoltage(volts: VoltageUnit) = motorController.setVoltage(volts.asVolts)
}

class BeaverTalonSRX(ID: Int) : BeaverMotorController {
    var motorController : TalonSRX = TalonSRX(ID)

    override fun get() = motorController.motorOutputPercent
    override fun setVoltage(volts: VoltageUnit) = motorController.set(TalonSRXControlMode.PercentOutput, volts.asVolts/12)
}
// uwo