package org.firstinspires.ftc.teamcode.BoofOpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "Jeff_TeleOp_v2.453278")
public class FishingMech extends LinearOpMode{
    ElapsedTime timer;

    @Override
    public void runOpMode() throws InterruptedException {

        timer = new ElapsedTime();

        DcMotor LeftR; //left rear motor
        DcMotor LeftF; //left front motor
        DcMotor RightR; //right rear motor
        DcMotor RightF; //right front motor
        DcMotor Lift; //back lift motor
        DcMotor Lift2; //lift second motor (closest to lift)
        DcMotor IntakeIn;//intake motor
        DcMotor IntakeUp;//flywheel motor


        Servo Booper; //front push servo
        Servo intakeRotation;//intake servo


        //discriminator = hardwareMap.get(ColorSensor.class, "colour");
        LeftR = hardwareMap.get(DcMotor.class, "left_r");
        LeftF = hardwareMap.get(DcMotor.class, "left_f");
        RightR = hardwareMap.get(DcMotor.class, "right_r");
        RightF = hardwareMap.get(DcMotor.class, "right_f");
        Lift = hardwareMap.get(DcMotor.class, "lift");
        Lift2 = hardwareMap.get(DcMotor.class, "lift2");
        IntakeIn = hardwareMap.get(DcMotor.class, "intake_in");
        IntakeUp = hardwareMap.get(DcMotor.class,"intake_up");



        Booper = hardwareMap.get(Servo.class, "booper");
        intakeRotation = hardwareMap.get(Servo.class, "rotate");


        LeftR.setDirection(DcMotor.Direction.REVERSE);
        LeftF.setDirection(DcMotor.Direction.FORWARD);
        RightR.setDirection(DcMotor.Direction.REVERSE);
        RightF.setDirection(DcMotor.Direction.FORWARD);
        Lift.setDirection(DcMotor.Direction.FORWARD);
        Lift2.setDirection(DcMotor.Direction.FORWARD);
        IntakeIn.setDirection(DcMotor.Direction.FORWARD);
        IntakeUp.setDirection(DcMotor.Direction.FORWARD);


        LeftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        IntakeIn.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        IntakeUp.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        IntakeUp.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Booper.setPosition(0);

        telemetry.addData("Ready", null);
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){

            //gamepad1

            final double DEADZONE = .05; // The trigger dead zone.
            if (gamepad1.right_trigger >= DEADZONE){
                RightF.setPower(-gamepad1.right_trigger);
                RightR.setPower(gamepad1.right_trigger);
                LeftR.setPower(-gamepad1.right_trigger);
                LeftF.setPower(gamepad1.right_trigger);
            } else if (gamepad1.left_trigger >= DEADZONE) {
                RightF.setPower(gamepad1.left_trigger);
                RightR.setPower(-gamepad1.left_trigger);
                LeftR.setPower(gamepad1.left_trigger);
                LeftF.setPower(-gamepad1.left_trigger);
            } else {
                LeftF.setPower(gamepad1.left_stick_y);
                LeftR.setPower(gamepad1.left_stick_y);
                RightF.setPower(gamepad1.right_stick_y);
                RightR.setPower(gamepad1.right_stick_y);
            }


            if (gamepad1.x) {
                Booper.setPosition(1);
            }
            else if (gamepad1.y){
                Booper.setPosition(0);
            }




            //gamepad 2
            if (gamepad2.dpad_down){
                Lift.setPower(1);
                Lift2.setPower(1);
            }
            else if (gamepad2.dpad_up){
                Lift.setPower(-1);
                Lift2.setPower(-1);
            }
            else {
                Lift.setPower(0);
                Lift2.setPower(0);
            }

            //IntakeUp
            if (Math.abs(gamepad2.left_stick_y) >= .05) {
                IntakeUp.setPower(gamepad2.left_stick_y);
            } else {
                IntakeUp.setPower(0);
            }

            //intakeIn
            if (Math.abs(gamepad2.left_trigger) >= .05) {
                IntakeIn.setPower(-gamepad2.left_trigger / 2);
            }
            else if (gamepad2.right_trigger >= .05){
                IntakeIn.setPower(gamepad2.right_trigger);
                }
             else {
                IntakeIn.setPower(0);
            }



            //Intake Servo
            if (gamepad2.left_bumper){
                intakeRotation.setPosition(0);
            }
            else if (gamepad2.right_bumper){
                intakeRotation.setPosition(1);
            }
        }
    }
}
