package beaverlib.odometry
// Filed adapted from 2898s 2023 Charged Up code
import edu.wpi.first.math.Matrix
import edu.wpi.first.math.Nat
import edu.wpi.first.math.geometry.*
import edu.wpi.first.math.numbers.N1
import edu.wpi.first.math.numbers.N3
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.beaverlib.misc.BiSignal
import frc.robot.beaverlib.odometry.BeaverVisionCamera
import org.photonvision.targeting.PhotonPipelineResult
import java.util.*

/**
 * A Vision SubsystemBase, that polls [cameras] each frame, and updates [listeners] with the apriltag results
 * @param cameras A list of all of the cameras connected to pi, to pull for apriltag results
 */
class BeaverPhotonVision(vararg val cameras: BeaverVisionCamera) : SubsystemBase() {
    /** Runs each of the Lambdas with the apriltag results, and the camera they came from, for every apriltag result. */
    val listeners = BiSignal<PhotonPipelineResult, BeaverVisionCamera>()

    override fun periodic() {
        for(camera in cameras){
            for (result in camera.results) {
                listeners.update(result, camera)
            }
        }
    }

    /** Updates all of the cameras pose estimators with the given reference pose */
    fun setAllCameraReferences(pose: Pose2d) {
        for(camera in cameras) {
            camera.setReference(pose)
        }
    }

    /** Returns the standard deviation matrix required by YAGSL given each of the standard deviation inputs
     * @param STDVX Standard deviation in the X estimation
     * @param STDVY Standard deviation in the Y estimation
     * @param rotationSTD Standard deviation in the rotation estimation
     * */
    fun getStandardDev(STDVX : Double, STDVY : Double, rotationSTD: Double): Matrix<N3, N1>{
        val stdv = Matrix(Nat.N3(), Nat.N1())
        stdv.set(0,0, STDVX)
        stdv.set(1,0, STDVY)
        stdv.set(2,0, rotationSTD)
        return stdv
    }
}