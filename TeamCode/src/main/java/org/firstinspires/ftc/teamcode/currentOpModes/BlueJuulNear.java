package org.firstinspires.ftc.teamcode.currentOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.internal.opmode.RegisteredOpModes;

import lib.fine.systems.SpeedyBot;
import lib.fine.vision.BetterVuforia;

import static android.view.Gravity.LEFT;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.RIGHT;

/**
 * Created by drew on 3/26/18.
 */
@Autonomous(name = "BlueBerry Juul Near V4.388")
public class BlueJuulNear extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        SpeedyBot robot = new SpeedyBot(this, DcMotor.RunMode.RUN_USING_ENCODER);
        BetterVuforia vuforia = new BetterVuforia(this, true);
        ElapsedTime runTime = new ElapsedTime();
        telemetry.addData("Ready", "Press Start");
        telemetry.update();
        vuforia.activate();
        waitForStart();

        robot.juulHittererer.hitBlue();

        robot.drive.strafeLeft(SpeedyBot.DRIVE_OFF_DISTANCE, 1);

        //RelicRecoveryVuMark column = vuforia.getColumn();

        sleep(800);
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

        //robot.drive.driveForward;
        //robot.drive.encoderTurn(-90,1 );
        //robot.drive.rotate(90, 0.7);
/*
        robot.drive.resetEncoders();
        while (robot.drive.drivingBackwardConsv(200, 1)) {
            idle();
        }
        robot.drive.stop();

        robot.drive.driveForward(80, 1);*/



        //robot.depositGlyph();

        //robot.drive.rotate(-angle, 0.7);

    }
}
