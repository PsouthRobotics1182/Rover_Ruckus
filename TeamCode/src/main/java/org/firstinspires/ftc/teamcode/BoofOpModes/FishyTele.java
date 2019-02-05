package org.firstinspires.ftc.teamcode.BoofOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//Created by Jeff on 11/21/18

@TeleOp(name = "Jeff_TeleOp_v1.326984")
public class FishyTele extends LinearOpMode {

    ElapsedTime timer;

    static final double Marker_Out = .2;
    static final double Marker_In = .1;

    static final double Extend_In = .2;
    static final double Extend_Out = .1;




    @Override
    public void runOpMode() throws InterruptedException{

        timer = new ElapsedTime();

        telemetry.addData("Ready", null);
        telemetry.update();

        //ColorSensor discriminator;
        DcMotor LeftR; //left rear motor
        DcMotor LeftF; //left front motor
        DcMotor RightR; //right rear motor
        DcMotor RightF; //right front motor
        DcMotor Lift; //back lift motor
        DcMotor Intake;//intake motor
        DcMotor Fly;//flywheel motor

        Servo Markermarker; //marker servo
        Servo XXXTension; //extends the robot
        Servo Boot; //boots in auto
        Servo CaptainHook; //Lift hook




        //discriminator = hardwareMap.get(ColorSensor.class, "colour");
        LeftR = hardwareMap.get(DcMotor.class, "left_r");
        LeftF = hardwareMap.get(DcMotor.class, "left_f");
        RightR = hardwareMap.get(DcMotor.class, "right_r");
        RightF = hardwareMap.get(DcMotor.class, "right_f");
        Lift = hardwareMap.get(DcMotor.class, "lift");
        Intake = hardwareMap.get(DcMotor.class, "intake");
        Fly = hardwareMap.get(DcMotor.class, "fly");

        Markermarker = hardwareMap.get(Servo.class, "marcus");
        XXXTension = hardwareMap.get(Servo.class, "Xtend");
        Boot = hardwareMap.get(Servo.class, "boot");
        //CaptainHook = hardwareMap.get(Servo.class, "hook");


        LeftR.setDirection(DcMotor.Direction.REVERSE);
        LeftF.setDirection(DcMotor.Direction.FORWARD);
        RightR.setDirection(DcMotor.Direction.REVERSE);
        RightF.setDirection(DcMotor.Direction.FORWARD);
        Lift.setDirection(DcMotor.Direction.FORWARD);
        Intake.setDirection(DcMotor.Direction.REVERSE);
        Fly.setDirection(DcMotor.Direction.FORWARD);

        LeftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Fly.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Boot.setPosition(1);

        waitForStart();

        while(opModeIsActive()){

            LeftR.setPower(gamepad1.left_stick_y);
            LeftF.setPower(gamepad1.left_stick_y);
            RightR.setPower(gamepad1.right_stick_y);
            RightF.setPower(gamepad1.right_stick_y);
            Intake.setPower(-gamepad2.right_trigger);
            Fly.setPower(gamepad2.left_trigger);

        //Gamepad 1
            //Mineral Boot

            if(gamepad1.dpad_left) {
                Boot.setPosition(.6);
            }
            else if (gamepad1.dpad_right) {
                Boot.setPosition(1);
            }

            //Drop Marker
            if(gamepad1.b) {
                Markermarker.setPosition(1);
            }
            else if(gamepad1.a) {
                Markermarker.setPosition(0);
            }

            //Extends robot
            if(gamepad1.x) {
                XXXTension.setPosition(.2);
            }
            else if(gamepad1.y) {
                XXXTension.setPosition(.9);
            }


        //Gamepad 2
            //Lift up and down
            //if(gamepad2.dpad_down) {
                //Lift.setPower(.3);
            //}
            //else if(gamepad2.dpad_up) {
                //Lift.setPower(-.3);
            //}
            //else {
                //Lift.setPower(0);
            //}






            idle();
        }
    }
}
