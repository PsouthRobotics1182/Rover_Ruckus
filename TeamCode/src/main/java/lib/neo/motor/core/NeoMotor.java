/**
 * NeoMotor | The core interface for motor implementations.
 * @author David Garland
 */

package lib.neo.motor.core;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public interface NeoMotor {
    /* Power */
    void set_power(double power);
    double get_power();
    void set_forward(boolean forward);
    void set_brake(boolean state);
    /* Encoders */
    void reset_pos();
    int get_true_pos();
    int zero_pos();
    int get_pos();
    /* Ticks */
    int ticks_per_rev();
    int mm_to_ticks(double mm);
    double ticks_to_mm(int ticks);
}
