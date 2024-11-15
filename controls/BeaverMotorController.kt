package beaverlib.controls

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.revrobotics.CANSparkLowLevel
import com.revrobotics.CANSparkLowLevel.MotorType
import com.revrobotics.CANSparkMax
import beaverlib.utils.Units.Electrical.VoltageUnit
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.revrobotics.CANSparkBase

interface BeaverMotorController {
    /** The percent output of the motor (-1.0.. 1.0 */
    fun get() : Double

    /** Runs the motor connected to the controller at the voltage "volts"
     *  (WARNING: May be unpredictable with non SparkMax controllers.)
     *  @param volts Voltage to run the motor at */
    fun setVoltage(volts : VoltageUnit)
    fun restoreFactoryDefaults()
    fun setCurrentLimit(currentLimit : Int)
    var idleMode : BeaverIdleMode
}
enum class BeaverIdleMode(val SparkValue : CANSparkBase.IdleMode, val SRXValue : NeutralMode) {
    UNSET(CANSparkBase.IdleMode.kCoast, NeutralMode.Coast),
    BREAK(CANSparkBase.IdleMode.kBrake, NeutralMode.Brake),
    COAST(CANSparkBase.IdleMode.kCoast, NeutralMode.Coast)}

class BeaverSparkMax(ID : Int, MotorType: CANSparkLowLevel.MotorType) : BeaverMotorController {
    var motorController : CANSparkMax = CANSparkMax(ID, MotorType)
    override fun get() = motorController.get()
    override fun setVoltage(volts: VoltageUnit) = motorController.setVoltage(volts.asVolts)
    override fun restoreFactoryDefaults() { motorController.restoreFactoryDefaults() }
    override fun setCurrentLimit(currentLimit: Int) { motorController.setSmartCurrentLimit(currentLimit) }
    override var idleMode: BeaverIdleMode = BeaverIdleMode.UNSET
        set(value) {
            motorController.idleMode = value.SparkValue
            field = value
        }
}

class BeaverTalonSRX(ID: Int) : BeaverMotorController {
    var motorController : TalonSRX = TalonSRX(ID)

    override fun get() = motorController.motorOutputPercent
    override fun setVoltage(volts: VoltageUnit) = motorController.set(TalonSRXControlMode.PercentOutput, volts.asVolts/12)
    override fun restoreFactoryDefaults() { motorController.configFactoryDefault() }
    override fun setCurrentLimit(currentLimit: Int) { motorController.configContinuousCurrentLimit(currentLimit) }
    override var idleMode: BeaverIdleMode = BeaverIdleMode.UNSET
        set(value) {
            motorController.setNeutralMode(value.SRXValue)
            field = value
        }
}