package org.firstinspires.ftc.teamcode.BoofOpModes.currentOpModes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.BoofOpModes.robotic;


@Autonomous (name= "Crater_Auto", group= "Autonomous")
public class CraterAuto extends robotic {
    /*
        lift down
        strafe off
        take picture
        booper down
        mineral
        turn backwards and spit marker out back
        turn and crater
         */

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

    @Override
    public void runOpMode() {

        LeftR = hardwareMap.get(DcMotor.class, "left_r");
        LeftF = hardwareMap.get(DcMotor.class, "left_f");
        RightR = hardwareMap.get(DcMotor.class, "right_r");
        RightF = hardwareMap.get(DcMotor.class, "right_f");


        LeftR.setDirection(DcMotor.Direction.REVERSE);
        LeftF.setDirection(DcMotor.Direction.REVERSE);

        //ERIC'S TEST AUTO MOTOR DIRECTION KEY!!!
        //Positive = forward, Negative = backward.


//Lift
        lift1 = hardwareMap.dcMotor.get("lift");
        lift2 = hardwareMap.dcMotor.get("lift2");

        lift1.setDirection(DcMotor.Direction.FORWARD);
        lift2.setDirection(DcMotor.Direction.FORWARD);
//Intake Arm
        intakeUp = hardwareMap.dcMotor.get("intake_up");
        intakeUp.setDirection(DcMotor.Direction.FORWARD);

        intakeIn = hardwareMap.dcMotor.get("intake_in");
        intakeIn.setDirection((DcMotor.Direction.FORWARD));


//SERVOS
//Intake In
        intakeRotation = hardwareMap.servo.get("rotate");

//Booper
        booper = hardwareMap.servo.get("booper");


//AUTONOMOUS STARTS
        telemetry.addData("Ready", null);
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {


            LeftF.setPower(.5);
            LeftR.setPower(.5);
            RightF.setPower(.5);
            RightR.setPower(.5);
            sleep(2000);

            LeftF.setPower(0);
            LeftR.setPower(0);
            RightF.setPower(0);
            RightR.setPower(0);


//AutoSETUP
////EXTENTION TEST
            intakeUp.setPower(-.7);
            sleep(1000);

            intakeUp.setPower(0);

            intakeIn.setPower(.6);
            sleep(2600);
            intakeIn.setPower(0);

            intakeUp.setPower(-.4);
            sleep(1000);
            break;



        }
    }
}