/**
 * NeoDrive | An interface defining the functions a drivetrain should offer.
 * @author David Garland
 */

package lib.neo.drive.core;

/**
 * Created by Robotics on 3/19/2018.
 */

public interface NeoDrive {
    /* Driving */

    boolean goes_fw(double mm, double power);
    void go_fw(double mm, double power, Runnable r);
    void go_fw(double mm, double power);

    boolean goes_bk(double mm, double power);
    void go_bk(double mm, double power, Runnable r);
    void go_bk(double mm, double power);

    boolean goes_le(double mm, double power);
    void go_le(double mm, double power, Runnable r);
    void go_le(double mm, double power);

    boolean goes_ri(double mm, double power);
    void go_ri(double mm, double power, Runnable r);
    void go_ri(double mm, double power);

    boolean rots(double deg, double power);
    void rot(double deg, double power, Runnable r);
    void rot(double deg, double power);

    /* Positioning */

    void set_gyro(boolean state);

    double pos_min();
    double pos_avg();
    double pos_max();
}
