package org.firstinspires.ftc.teamcode.currentOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import lib.fine.core.FineIMU;
import lib.fine.systems.SpeedyBot;
import lib.fine.vision.BetterVuforia;

/**
 * Created by drew on 11/25/17.
 */
@Autonomous(name = "BlueBerry Juul V4.386")
public class BlueJuul extends LinearOpMode {

      //////////////////////
     // Global Variables //
    //////////////////////

    BetterVuforia vuforia;
    RelicRecoveryVuMark column;

      //////////////////////
     // Helper Functions //
    //////////////////////

    /**
     * Update the current column if it is unknown.
     */

    public RelicRecoveryVuMark updateColumn() {
        return (column == RelicRecoveryVuMark.UNKNOWN) ? vuforia.getColumn() : column;
    }

      /////////////////
     // Main OpMode //
    /////////////////

    @Override
    public void runOpMode() throws InterruptedException {
          ////////////////////
         // Initialization //
        ////////////////////

        SpeedyBot robot = new SpeedyBot(this, DcMotor.RunMode.RUN_USING_ENCODER);
        vuforia = new BetterVuforia(this, true);
        ElapsedTime runTime = new ElapsedTime();
        vuforia.activate();

        telemetry.addData("Ready", null);
        telemetry.update();

        waitForStart();
        runTime.reset();

        column = vuforia.getColumn();


        robot.juulHittererer.hitBlue();


        robot.drive.resetEncoders();
        //double power = 0;
        robot.drive.strafeLeft(SpeedyBot.DRIVE_OFF_DISTANCE, 1);
        robot.drive.stop();

        column = vuforia.getColumn();

        robot.drive.imu.setMode(FineIMU.Mode.ON_PAD);
        //robot.sleep(5000);

        robot.drive.resetEncoders();
        runTime.reset();
        while (robot.drive.drivingBackwardConsv(600, 0.6)) {
            idle();
            updateColumn();
        }

        robot.drive.stop();

        robot.drive.imu.setMode(FineIMU.Mode.OFF_PAD);

        while (robot.drive.drivingForwardNoGyro(100, 1)) {
            idle();
        }
        robot.sleep(1000);
        column = vuforia.getColumn();

        robot.drive.setAngleTolerence(3);

        robot.drive.rotate(-90, 0.7);

        telemetry.addData("Column", column);
        telemetry.update();
        //sleep(5000);
        int mmBase = 465;
        int mmStrafe = mmBase;

        if (column == RelicRecoveryVuMark.UNKNOWN) column = RelicRecoveryVuMark.CENTER;
        switch(column) {
            case CENTER:
                mmStrafe = mmBase + 160;
                break;
            case LEFT:
                mmStrafe = mmBase;
                break;
            case RIGHT:
                mmStrafe = mmBase + 160 + 160;
                break;
        }

        robot.drive.setAngleTolerence(5);

        runTime.reset();
        robot.drive.resetEncoders();
        while (robot.drive.strafingRange(mmStrafe, 0.8) && runTime.milliseconds() < 7000) {
            idle();
        }
        robot.drive.stop();
        //robot.drive.strafeRange(mmStrafe, 0.8);

        //robot.leftMotor.leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        /*while (robot.drive.imu.getHeading() < 90 && opModeIsActive()) {
            idle();
            robot.drive.rotating(1);
        }*/

        robot.drive.encoderTurn(180,0.7);
        robot.drive.rotate(90, 0.7);


        robot.suckyBois.boot(0);
        robot.drive.resetAngle();
        robot.depositGlyph();


    }
}
