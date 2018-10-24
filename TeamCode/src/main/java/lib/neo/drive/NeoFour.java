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
    private boolean gyroMode = false;

    public NeoFour(LinearOpMode opMode, float circumference) {
        this.opMode = opMode;
        this.wheel = new NeoMotor[2][2];
        this.wheel[0][0] = new NeoDc(this.opMode, "frontLeft", circumference);
        this.wheel[0][1] = new NeoDc(this.opMode, "frontRight", circumference);
        this.wheel[1][0] = new NeoDc(this.opMode, "rearLeft", circumference);
        this.wheel[1][1] = new NeoDc(this.opMode, "rearRight", circumference);
    }

    public boolean goes_fw(double mm, double power) {
        // TODO: Gyro headings / IMU.
        final double distance = Math.abs(this.wheel[0][0].mm_to_ticks(mm));
        final double pos = Math.abs(pos_min());
        if ((pos < distance) && opMode.opModeIsActive()) {
            for (int i = 0; i < 2; i++)
                for (int j = 0; j < 2; j++)
                    wheel[i][j].set_power(power);
            return true;
        }
        return false;
    }

    public void go_fw(double mm, double power) {
        go_fw(mm, power, new NeoRunnables.emptyRunnable());
    }

    public void go_fw(double mm, double power, Runnable r) {
        while (goes_fw(mm, power)) r.run();
    }

    public boolean goes_bk(double mm, double power) {
        // TODO: Gyro headings / IMU.
        final double distance = Math.abs(this.wheel[0][0].mm_to_ticks(mm));
        final double pos = Math.abs(pos_min());
        if ((pos < distance) && opMode.opModeIsActive()) {
            for (int i = 0; i < 2; i++)
                for (int j = 0; j < 2; j++)
                    wheel[i][j].set_power(-Math.abs(power));
            return true;
        }
        return false;
    }

    public void go_bk(double mm, double power) {
        go_bk(mm, power, new NeoRunnables.emptyRunnable());
    }

    public void go_bk(double mm, double power, Runnable r) {
        while (goes_bk(mm, power)) r.run();
    }

    // TODO: Implement these when we actually need them. Not very useful for 4WD.
    public boolean goes_le(double mm, double power) { return false; }
    public void go_le(double mm, double power) { go_le(mm, power, new NeoRunnables.emptyRunnable()); }
    public void go_le(double mm, double power, Runnable r) { return; }
    public boolean goes_ri(double mm, double power) { return false; }
    public void go_ri(double mm, double power) { go_ri(mm, power, new NeoRunnables.emptyRunnable()); }
    public void go_ri(double mm, double power, Runnable r) { while(goes_ri(mm, power)) r.run(); }

    public boolean rots(double deg, double power) {
        return false; // TODO: Implementation.
    }

    public void rot(double deg, double power) {
        rot(deg, power, new NeoRunnables.emptyRunnable());
    }

    public void rot(double deg, double power, Runnable r) {
        while(rots(deg, power)) r.run();
    }

    /* Position Checking */

    public void set_gyro(boolean state) {

    }

    public double pos_min() {
        double min = Float.MAX_VALUE;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                final double pos = this.wheel[i][j].get_pos();
                min = pos < min ? min : pos;
            }
        }
        return min;
    }

    public double pos_max() {
        double max = 0.0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                final double pos = this.wheel[i][j].get_pos();
                max = pos > max ? max : pos;
            }
        }
        return max;
    }

    public double pos_avg() {
        double sum = 0.0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                sum += this.wheel[i][j].get_pos();
            }
        }
        return sum / 4;
    }
}
