package org.firstinspires.ftc.teamcode.currentOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import lib.fine.systems.Flipper;
import lib.fine.systems.SpeedyBot;
import lib.fine.vision.BetterVuforia;

/**
 * Created by drew on 3/26/18.
 */
@Autonomous(name = "BlueBerry Juul Near Multi V4.388")
public class BlueJuulNearMulti extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        SpeedyBot robot = new SpeedyBot(this, DcMotor.RunMode.RUN_USING_ENCODER);
        BetterVuforia vuforia = new BetterVuforia(this, true);
        ElapsedTime runTime = new ElapsedTime();
        ElapsedTime matcchTIme = new ElapsedTime();

        telemetry.addData("Ready", "Press Start");
        telemetry.update();
        vuforia.activate();
        waitForStart();
        matcchTIme.reset();

        robot.juulHittererer.hitBlue();

        robot.drive.strafeLeft(SpeedyBot.DRIVE_OFF_DISTANCE, 1);

        //RelicRecoveryVuMark column = vuforia.getColumn();

        //sleep(800);
        RelicRecoveryVuMark column = vuforia.getColumn();
        telemetry.addData("VuMark", "%s visible", column);
        telemetry.update();
        //sleep(5000);
        /*robot.drive.resetEncoders();
        while (robot.drive.drivingForwardConserv(720, 0.6, 0)) {
            idle();
        }
        robot.drive.stop();*/

        robot.drive.driveBackward(290, 1);
        column = vuforia.getColumn();
        //robot.drive.encoderTurn(-45, 1);

        int angle = -90;
        if (column == RelicRecoveryVuMark.UNKNOWN)
            column = RelicRecoveryVuMark.RIGHT;
        if (column == RelicRecoveryVuMark.RIGHT)
            angle = -127;
        else if (column == RelicRecoveryVuMark.CENTER)
            angle = -115;
        else if (column == RelicRecoveryVuMark.LEFT)
            angle = -90;


        robot.drive.encoderTurn(angle/2, 0.7);

        robot.drive.setAngleTolerence(2);
        robot.drive.rotateNoReser(angle, 0.4);
        robot.sleep(1000);
        robot.drive.rotate(angle, 0.7);

        if (column == RelicRecoveryVuMark.LEFT) {
            robot.drive.setAngleTolerence(5);
            robot.drive.strafeRangeOtherSide(1000, 0.8);
        }

        robot.sleep(500);

        robot.drive.imu.resetAngle();
        //robot.drive.driveForward(100, 1);

        robot.drive.resetEncoders();
        while (robot.drive.drivingForwardNoGyro(300, 1)) {
            idle();
        }
        robot.drive.stop();

        robot.suckyBois.setPower(-1);
        robot.sleep(500);
        robot.drive.driveBackward(150, 1);
        robot.suckyBois.setPower(0);

        robot.drive.encoderTurn(-angle/2 + 90/2, 1);
        robot.drive.stop();

        robot.lift.unBlock();
        robot.suckyBois.setPower(1);
        robot.sleep(500);
        robot.drive.resetEncoders();
        while (robot.drive.drivingForwardNoGyro(330, 1)) {
            idle();
        }
        robot.drive.stop();


        //robot.sleep(1000);

        robot.drive.setAngleTolerence(5);

        robot.drive.resetEncoders();
        while (robot.drive.drivingBackwardConsv(180, 1)) {
            idle();
        }
        robot.drive.stop();

        robot.drive.imu.resetAngle();
        robot.drive.rotate(0, 0.7);


        if (matcchTIme.milliseconds() > 28000)
            return;
        /*robot.drive.imu.resetAngle();
        if (robot.drive.getRange() < 1020)
            robot.drive.strafeRange(1020,1 );*/

        robot.drive.setAngleTolerence(5);
        robot.drive.resetEncoders();
        while (robot.drive.drivingBackwardConsv(150, 1)) {
            idle();
        }
        robot.drive.stop();
        robot.suckyBois.boot(1);

        robot.sleep(1000);

        robot.lift.flip(Flipper.VERTICAL);

        robot.sleep(1000);


        robot.drive.resetEncoders();
        while (robot.drive.drivingForwardNoGyro(80, 1)) {
            idle();
        }
        robot.drive.stop();

        robot.suckyBois.setPower(0);



        //robot.depositGlyph();

        //robot.drive.rotate(-angle, 0.7);

    }
}
