/**
 * NeoIMU |
 */

package lib.neo.drive;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class NeoIMU {
    private BNO055IMU imu;
    private Orientation angles;
    private ElapsedTime runTime;
    private LinearOpMode opMode;
    private String name;

    private double prevError = 0;
    private double integral = 0;
    private double zeroPos = 0;

    private static final double kP = 0.02;
    private static final double kI = 0.0;
    private static final double kD = 0.24;

    /**
     * The constructor for the NeoIMU class.
     * @param opMode The opmode used for the robot.
     * @param name The name of the IMU.
     */

    public NeoIMU(LinearOpMode opMode, String name) {
        this.opMode = opMode;
        this.name = name;
        this.runTime = new ElapsedTime();
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = opMode.hardwareMap.get(BNO055IMU.class, name);
        imu.initialize(parameters);                                    //how often to update values
        imu.startAccelerationIntegration(new Position(), new Velocity(), 100);
    }

    public double getHeading() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return angles.firstAngle - zeroPos;
    }

    public double getRate() {
        return imu.getAngularVelocity().zRotationRate;
    }

    public double[] getLevelness() {
        double[] levels = new double[2];
        levels[0] = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).secondAngle;
        levels[1] = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).thirdAngle;
        return levels;
    }

    public Acceleration getAcceleration() {
        return imu.getAcceleration();
    }

    public Velocity getVelocity() {
        return imu.getVelocity();
    }

    public Position getPosition() {
        return imu.getPosition();
    }

    public void resetPID() {
        prevError = 0;
        integral = 0;
    }

    public void resetAngle() {
        zeroPos = getHeading();
    }

    public double align (double angle) {
        if (prevError == 0) runTime.reset();
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        final double heading = getHeading();
        final double error = angle-heading;
        integral = integral + error * kP *runTime.milliseconds();
        final double derivative = (error - prevError)/runTime.milliseconds();
        final double correction = error * kP + derivative * kD + integral * kI;
        //correction = Range.clip(correction, 0, 0.3);
        prevError = error;
        runTime.reset();
        return correction;
    }
}
