package org.firstinspires.ftc.teamcode.BoofOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

@Autonomous(name= "Depo Auto", group = "Auto")

public class Auto extends robotic
{

    DcMotor LeftR;   //3
    DcMotor LeftF;   //1
    DcMotor RightR;  //4
    DcMotor RightF;  //2

    DcMotor lift1;
    DcMotor lift2;
    DcMotor intakeUp;
    DcMotor intakeIn;

    Servo booper;
    Servo intakeRotation;


    public void runOpMode() {
        init(0);

        waitForStart();

        while (opModeIsActive()) {
            int position = position();

            if (position == 1) {
                telemetry.addData("gold", "right");
                telemetry.update();
            }
            if (position == 2) {
                telemetry.addData("gold", "center");
                telemetry.update();
                forward(1, 1);
            }
            if (position == 3) {
                telemetry.addData("gold", "left");
                telemetry.update();
            }
            sleep(9999);

            //Drivetrain
            LeftF = hardwareMap.dcMotor.get("left_f");
            LeftR = hardwareMap.dcMotor.get("left_r");
            RightR = hardwareMap.dcMotor.get("right_r");
            RightF = hardwareMap.dcMotor.get("right_f");

       /*
        LeftR = hardwareMap.get(DcMotor.class, "left_r");
        LeftF = hardwareMap.get(DcMotor.class, "left_f");
        RightR = hardwareMap.get(DcMotor.class, "right_r");
        RightF = hardwareMap.get(DcMotor.class, "right_f");
        */

            //LeftR.setDirection(DcMotor.Direction.REVERSE);
            //LeftF.setDirection(DcMotor.Direction.REVERSE);

            //ERIC'S TEST AUTO MOTOR DIRECTION KEY!!!
            //Positive = forward, Negative = backward.

            LeftR.setDirection(DcMotor.Direction.FORWARD);
            LeftF.setDirection(DcMotor.Direction.REVERSE);
            RightR.setDirection(DcMotor.Direction.FORWARD);
            RightF.setDirection(DcMotor.Direction.REVERSE);

//AUTONOMOUS STARTS
            telemetry.addData("Ready", null);
            telemetry.update();
            waitForStart();

            while (opModeIsActive()) {
/*

     ***COMPLETED CODE***


        //LIFT UP
        lift1.setPower(-1);
        lift2.setPower(-1);
        sleep(8000);
        lift1.setPower(0);
        lift2.setPower(0);


//Shimmy out of lander
//Turn Right
        LeftF.setPower(-1); //left forward
        LeftR.setPower(-1);
        RightF.setPower(1);
        RightR.setPower(1);
        sleep(1000);

//Turn Left
        LeftF.setPower(1);
        LeftR.setPower(1);
        RightF.setPower(-1);
        RightR.setPower(-1);
        sleep(500);


//Turn Back to position
        LeftF.setPower(-.7); //left forward
        LeftR.setPower(-.7);
        RightF.setPower(.7);
        RightR.setPower(.7);
        sleep(700);

        LeftF.setPower(0); //left forward
        LeftR.setPower(0);
        RightF.setPower(0);
        RightR.setPower(0);


//LIFT down
        lift1.setPower(1);
        lift2.setPower(1);
        sleep(8000);
        lift1.setPower(0);
        lift2.setPower(0);
//Booper down
        booper.setPosition(1);
        sleep(2300);

//Go Backward
        LeftF.setPower(-0.5)
        LeftR.setPower(-0.5);
        RightF.setPower(-0.5);
        RightR.setPower(-0.5);
        sleep(1000);

        LeftF.setPower(0);
        LeftR.setPower(0);
        RightF.setPower(0);
        RightR.setPower(0);



        */

//Intake Arm goes up
                //  intakeUp.setPower(-1);
                //    sleep(700);


//Mineral Scan
//*INSERT CODE**
//Throw Mineral
                intakeUp.setPower(-.7);
                sleep(3000);
                intakeUp.setPower(0);

//turn towards crater
                LeftF.setPower(-.5);
                RightF.setPower(.5);
                LeftR.setPower(-.5);
                RightR.setPower(.5);
                sleep(750);

//Move into crater
                LeftF.setPower(1);
                RightF.setPower(1);
                LeftR.setPower(1);
                RightR.setPower(1);
                sleep(2000);


                intakeUp.setPower(-1);
                sleep(1500);

// Intake in & up

                intakeUp.setPower(1);
                sleep(500);
                intakeIn.setPower(-.2);
                sleep(5000);
        }
    }
    }
}
