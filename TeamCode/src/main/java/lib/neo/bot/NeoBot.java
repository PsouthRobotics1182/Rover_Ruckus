/**
 * NeoBot | The master class for the PSH Robotics 2018-2019 bots.
 * @author David Garland
 */

package lib.neo.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import lib.neo.drive.core.NeoDrive;
import lib.neo.drive.NeoFour;

public class NeoBot {
    LinearOpMode opMode;
    NeoDrive driveTrain;

    public NeoBot(LinearOpMode opMode) {
        this.opMode = opMode;
        this.driveTrain = new NeoFour(opMode, 90 * 2); /* 90mm, 1:2 gear ratio */
    }
}
