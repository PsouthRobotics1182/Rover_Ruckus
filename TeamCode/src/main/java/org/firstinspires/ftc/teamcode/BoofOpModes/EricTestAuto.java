package org.firstinspires.ftc.teamcode.BoofOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;


@Autonomous (name= "Eric_Crappie_Auto", group= "Autonomous")
public class EricTestAuto extends robotic {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "AYWilRL/////AAABmf7FFVPzZUkeobQQQGUbh1EDt8vYvnEgdvhH/pgFaw+cAntRcDT0A2P+ANN0kBTBRbL0TkkveZytIzkczswGdS/Bd4DqbEGVzXRSpO4CGWRgqzrUcbIkJc2MengACTvq5PfpACG2aJwbiK/Y6ff6hMFzeqlJOs5B1Rx8gzOa4vI04GYY2QMChWvtr+uBKbToGEwrDUPf5hcJKxZraeu3tmw+GjKw+G5I0poG2Eu1D3vyKzzFwNZQFkto5GiI6t6JI2HwX/IMwoWlkNx3+t00cCvV6pNp5kuJ04gYEAb4zMwol1FiMLMytUX2iYvXMuz4kq/uC9ZAmUSZu9ARPcBpI9V0gUU9QbsDU1h8pzxqtoJu";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
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

        LeftR.setDirection(DcMotor.Direction.FORWARD);
        LeftF.setDirection(DcMotor.Direction.REVERSE);
        RightR.setDirection(DcMotor.Direction.FORWARD);
        RightF.setDirection(DcMotor.Direction.REVERSE);

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

    /*strafe left test */

        //LIFT UP
        lift1.setPower(-.7);
        lift2.setPower(-.7);
        sleep(9100);
        lift1.setPower(0);
        lift2.setPower(0);

        //reverse
        LeftF.setPower(-.5);
        LeftR.setPower(-.5);
        RightF.setPower(-.5);
        RightR.setPower(-.5);
        sleep(1000);

    //shimmy test
        LeftF.setPower(.5); //left forward
        LeftR.setPower(.5);
        RightF.setPower(-.5);
        RightR.setPower(-.5);
        sleep(3000);

    }
}



