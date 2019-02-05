package org.firstinspires.ftc.teamcode.BoofOpModes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;




@Autonomous (name = "Boofv1.32798")
public class FishyAuto extends LinearOpMode {


    private final double COUNTS_PER_REV = 288;
    private final double DRIVE_GEAR_REDUCTION = 40 / 15;
    private final double WHEEL_DIAMETER_MM = 90;
    final double COUNTS_PER_INCH = (COUNTS_PER_REV * DRIVE_GEAR_REDUCTION * WHEEL_DIAMETER_MM);

    final double DRIVE_SPEED = .7; //slower for better accuracy
    final double TURN_SPEED = .5; //slower for better accuracy

    final double HEADING_THRESHOLD = 1; //As tight as possible with integers
    final double P_TURN_COEFF = .1; //Larger is more responsive, but also less stable
    final double P_DRIVE_COEFF = .1; //Larger is more responsive, but also less stable


    @Override
    public void runOpMode() throws InterruptedException {
//Instance Variables
        ColorSensor colorSensor;
        DcMotor LeftR;
        DcMotor LeftF;
        DcMotor RightR;
        DcMotor RightF;
        DcMotor Lift;
        Servo Markermarker; //marker servo
        Servo Boot; //boots in auto
        //Servo CaptainHook;


        colorSensor = hardwareMap.get(ColorSensor.class, "colour");
        LeftR = hardwareMap.get(DcMotor.class, "left_r");
        LeftF = hardwareMap.get(DcMotor.class, "left_r");
        RightR = hardwareMap.get(DcMotor.class, "right_r");
        RightF = hardwareMap.get(DcMotor.class, "right_r");
        Lift = hardwareMap.get(DcMotor.class, "lift");
        Markermarker = hardwareMap.get(Servo.class, "marcus");
        Boot = hardwareMap.get(Servo.class, "boot");
        //CaptainHook = hardwareMap.get(Servo.class, "hook");


        //float hsvValues[] = {0F, 0F, 0F};
        //final float values[] = hsvValues;
        //final double SCALE_FACTOR = 255;

        ElapsedTime runTime = new ElapsedTime();


        //discriminator.enableLed(true);
        Markermarker.setPosition(-1);

        LeftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Boot.setPosition(1);



        LeftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //resets encoders
        LeftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        final double circumference = (Math.PI * WHEEL_DIAMETER_MM); //pi times diameter of the wheel = circumference
        final double rotationsneeded = ((1500/circumference) / DRIVE_GEAR_REDUCTION * .745);//number of mm divided by circumference
        final int encoderdrivingtarget = (int)(rotationsneeded*COUNTS_PER_REV);//number of ticks needed to drive distance

        LeftF.setTargetPosition(encoderdrivingtarget);
        LeftR.setTargetPosition(encoderdrivingtarget);
        RightF.setTargetPosition(encoderdrivingtarget);
        RightR.setTargetPosition(encoderdrivingtarget);

        telemetry.addData("Ready", null);
        telemetry.update();

        waitForStart();
//AUTONOMOUS STARTS



        //Lower Lift
        //Lift.setPower(1);
        //sleep(500);
        //Lift.setPower(0);

        //Undo Hook
        //CaptainHook.setPosition(1);


        //Drive Forward
        LeftR.setPower(-.5);
        LeftF.setPower(-.5);
        RightR.setPower(-.5);
        RightF.setPower(-.5);
        LeftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        //Rest Hook
        //CaptainHook.setPosition(0);


        while(LeftF.isBusy() || LeftR.isBusy() || RightF.isBusy() || RightR.isBusy()){
            telemetry.addData("Driving", "1500 mm");
            telemetry.update();
        }

        LeftR.setPower(0);
        LeftF.setPower(0);
        RightR.setPower(0);
        RightF.setPower(0);



        //Lineup for Mineral Run 1

        //Color.RGBToHSV((int) (discriminator.red() * SCALE_FACTOR),
                //(int) (discriminator.green() * SCALE_FACTOR),
                //(int) (discriminator.blue() * SCALE_FACTOR),
                //hsvValues);

        //Boot.setPosition(.6);
        //sleep(1000);
        //stop();




        //Marker
        Markermarker.setPosition(.2);
        sleep(3000);


        //Lineup for 2nd Mineral Run











        runTime.reset();


    }
}
