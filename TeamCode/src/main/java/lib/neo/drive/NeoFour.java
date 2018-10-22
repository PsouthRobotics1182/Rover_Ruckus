/**
 * NeoFour | An implementation of NeoDrive for 4-wheel drivetrains.
 * @author David Garland
 */

package lib.neo.drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import lib.neo.motor.core.NeoMotor;
import lib.neo.motor.NeoDc;
import lib.neo.drive.core.NeoDrive;
import lib.neo.misc.NeoRunnables;

public class NeoFour implements NeoDrive {
    private LinearOpMode opMode;
    private NeoMotor wheel[][];

    public NeoFour(LinearOpMode opMode, float circumference) {
        this.opMode = opMode;
        this.wheel = new NeoMotor[2][2];
        this.wheel[0][0] = new NeoDc(this.opMode, "frontLeft", circumference);
        this.wheel[0][1] = new NeoDc(this.opMode, "frontRight", circumference);
        this.wheel[1][0] = new NeoDc(this.opMode, "rearLeft", circumference);
        this.wheel[1][1] = new NeoDc(this.opMode, "rearRight", circumference);
    }

    public boolean goes_fw(float mm) {
        return false; // TODO: Implementation.
    }

    public void go_fw(float mm) {
        go_fw(mm, new NeoRunnables.emptyRunnable());
    }

    public void go_fw(float mm, Runnable r) {
        while (goes_fw(mm)) r.run();
    }

    public boolean goes_bk(float mm) {
        return goes_fw(-mm);
    }

    public void go_bk(float mm) {
        go_bk(mm, new NeoRunnables.emptyRunnable());
    }

    public void go_bk(float mm, Runnable r) {
        while (goes_bk(mm)) r.run();
    }

    public boolean goes_le(float mm) {
        return goes_ri(mm); // TODO: Implementation.
    }

    public void go_le(float mm) {
        go_le(mm, new NeoRunnables.emptyRunnable());
    }

    public void go_le(float mm, Runnable r) {
        while(goes_le(mm)) r.run();
    }

    public boolean goes_ri(float mm) {
        return false; // TODO: Implementation.
    }

    public void go_ri(float mm) {
        go_ri(mm, new NeoRunnables.emptyRunnable());
    }

    public void go_ri(float mm, Runnable r) {
        while(goes_ri(mm)) r.run();
    }

    public boolean rots(float deg) {
        return false; // TODO: Implementation.
    }

    public void rot(float deg) {
        rot(deg, new NeoRunnables.emptyRunnable());
    }

    public void rot(float deg, Runnable r) {
        while(rots(deg)) r.run();
    }
}
