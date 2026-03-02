package frc.engine.utils

// File adapted from 2898's bpsrobotics engine
import kotlin.math.pow

/**
 * Creates a polynomial using the given coefficients
 *
 * @param coefficients the coefficients of the terms of the polynomial, in order of greatest power
 *   of x to least
 */
class Polynomial(vararg val coefficients: Double) {
    fun calculate(value: Double): Double {
        var returnValue = 0.0
        for (i: Int in 0..<coefficients.size) {
            returnValue += coefficients[i] * (value.pow(coefficients.size - i - 1))
        }
        return returnValue
    }

    fun calculate(value: Int): Double = calculate(value.toDouble())
}
