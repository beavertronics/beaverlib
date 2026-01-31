package beaverlib.fieldmap

import beaverlib.utils.Units.Linear.feet
import beaverlib.utils.Units.Linear.inches
import beaverlib.utils.geometry.Line
import beaverlib.utils.geometry.Rectangle
import beaverlib.utils.geometry.Vector2
import edu.wpi.first.wpilibj.DriverStation

interface Hub {
    val center: Vector2
    val shape: Rectangle
}

interface Trench {
    val centerX: Double
    val line: Line
}

object FieldMapREBUILTWelded {
    val FieldLength = 54.feet + 8.75.inches
    val FieldHeight = 26.feet + 4.inches
    val HubWidth = 46.508.inches.asMeters

    object RedHub : Hub {
        override val center = Vector2(182.11.inches.asMeters, 158.84.inches.asMeters)
        override val shape =
            Rectangle(
                center - Vector2(HubWidth, HubWidth) / 2.0,
                center + Vector2(HubWidth, HubWidth) / 2.0,
            )
    }

    val teamHub
        get() =
            if (DriverStation.getAlliance().get() == DriverStation.Alliance.Red) {
                RedHub
            } else {
                BlueHub
            }

    object BlueHub : Hub {
        override val center =
            Vector2(651.22.inches.asMeters - 182.11.inches.asMeters, 158.84.inches.asMeters)
        override val shape =
            Rectangle(
                center - Vector2(HubWidth, HubWidth) / 2.0,
                center + Vector2(HubWidth, HubWidth) / 2.0,
            )
    }

    val trenches = arrayOf(TopRedTrench, BottomRedTrench, TopBlueTrench, BottomBlueTrench)

    object TopRedTrench : Trench {
        override val centerX: Double = 182.11.inches.asMeters
        override val line: Line =
            Line(
                Vector2(centerX, 317.69.inches.asMeters),
                Vector2(centerX, (317.69 - 50.59).inches.asMeters),
            )
        val shape: Rectangle
            get() = TODO("Not yet implemented")
    }

    object BottomRedTrench : Trench {
        override val centerX: Double = 182.11.inches.asMeters
        override val line: Line =
            Line(Vector2(centerX, 0.0.inches.asMeters), Vector2(centerX, 50.59.inches.asMeters))
        val shape: Rectangle
            get() = TODO("Not yet implemented")
    }

    object TopBlueTrench : Trench {
        override val centerX: Double = 469.11.inches.asMeters
        override val line: Line =
            Line(
                Vector2(centerX, 317.69.inches.asMeters),
                Vector2(centerX, (317.69 - 50.59).inches.asMeters),
            )
        val shape: Rectangle
            get() = TODO("Not yet implemented")
    }

    object BottomBlueTrench : Trench {
        override val centerX: Double = 469.11.inches.asMeters
        override val line: Line =
            Line(Vector2(centerX, 0.0.inches.asMeters), Vector2(centerX, 50.59.inches.asMeters))
        val shape: Rectangle
            get() = TODO("Not yet implemented")
    }
}
