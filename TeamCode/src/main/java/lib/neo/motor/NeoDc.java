/**
 * NeoDc | An implementation of NeoMotor for DC motors.
 * @author David Garland
 */

package lib.neo.motor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import lib.neo.motor.core.NeoMotor;

public class NeoDc implements NeoMotor {
    public final float circumference;
    private LinearOpMode opMode;
    private DcMotor motor;
    private int zeroPos = 0;

    /**
     * The constructor for the NeoDc implementation of NeoMotor.
     * @param opMode The opmode used for the robot.
     * @param name The name of the motor, as specified in the mobile app.
     * @param circumference The circumference of the wheel the motor is attached to.
     */

    public NeoDc(LinearOpMode opMode, String name, float circumference) {
        this.opMode = opMode;
        this.motor = this.opMode.hardwareMap.get(DcMotor.class, name);
        this.circumference = circumference;
    }

    /* Power */

    /**
     * Sets the volatage level of the motor.
     * @param power The power level, ranging from 0 to 1.
     */

    public void set_power(double power) {
        motor.setPower(power);
    }

    /**
     * Gets the voltage level of the motor.
     * @return The power level, ranging from 0 to 1.
     */

    public double get_power() {
        return motor.getPower();
    }

    /**
     * Sets the direction of the motor. If true, then the motor will spin forwards, otherwise the
     * motor will spin backwards.
     * @param forward Whether the motor will spin forwards or backwards.
     */

    public void set_forward(boolean forward) {
        if (forward)
            motor.setDirection(DcMotor.Direction.FORWARD);
        else
            motor.setDirection(DcMotor.Direction.REVERSE);
    }

    /**
     * Sets the state of the brakes. If true, then the motors will brake when subjected to zero
     * power. Otherwise, the motors will spin freely.
     * @param brake Whether or not to brake under zero power.
     */

    public void set_brake(boolean brake) {
        if (brake)
            this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        else
            this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    /* Encoders */

    /**
     * Resets the position of the motor. More specifically, sets the internal zero position to the
     * current position of the motor.
     */

    public void reset_pos() {
        this.zeroPos = get_true_pos();
    }

    /**
     * Returns the zero position of the motor.
     * @return Zero position. Stored in {link zeroPos}.
     */

    public int zero_pos() {
        return this.zeroPos;
    }

    /**
     * Finds the true position of the motor.
     * @return Current position.
     */

    public int get_true_pos() {
        return motor.getCurrentPosition();
    }

    /**
     * Finds the current position of the motor relative to the zero position.
     * @return Current position. Determined by {link #get_true_pos} - {link #zero_pos}.
     */

    public int get_pos() {
        return get_true_pos() - zero_pos();
    }

    /* Ticks */

    /**
     * Finds the ticks required per revolution of the motor.
     * @return Ticks per revolution.
     */

    public int ticks_per_rev() {
        return motor.getMotorType().getAchieveableMaxTicksPerSecondRounded();
    }

    /**
     * Converts millimeters to ticks for motor rotation.
     * @param mm The number of millimeters to turn.
     * @return The number of ticks to turn.
     */

    public int mm_to_ticks(double mm) {
        final double rotations = mm / circumference;
        return this.ticks_per_rev() * (int) rotations;
    }

    /**
     * Converts ticks to millimeters for motor rotation.
     * @param ticks The number of ticks to turn.
     * @return The number of millimeters to turn.
     */

    public double ticks_to_mm(int ticks) {
        final double rotations = ticks / this.ticks_per_rev();
        return rotations * this.circumference;
    }
}
