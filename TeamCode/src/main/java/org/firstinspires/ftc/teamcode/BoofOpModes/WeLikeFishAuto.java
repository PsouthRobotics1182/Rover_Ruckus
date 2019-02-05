package org.firstinspires.ftc.teamcode.BoofOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;


@Autonomous (name= "NTB_Likes_Fish", group= "Autonomous")
public class WeLikeFishAuto extends robotic {
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
    /*
        int position = position();
        if (position == 1) {
            strafe(-0.3, (int) 25.4 * 10);
            forward(0.3, (int) 25.4 * 10);
            turn(-.3, 90);
        } else if (position == 2) {
            forward(0.3, (int) 25.4 * 10);
            turn(-.3, 90);
            forward(0.3, (int) 25.4 * 10);
        } else if (position == 3) {
            strafe(0.3, (int) 25.4 * 10);
            forward(0.3, (int) 25.4 * 10);
            turn(-.3, 90);
            forward(0.3, (int) 25.4 * 20);
        } else {
            forward(0.3, (int) 25.4 * 5);
            turn(-.3, 90);
            forward(0.3, (int) 25.4 * 20);
        }

        sleep(5000);
*/
/*

            int position = position();

            if (position == 1) {
                telemetry.addData("gold", "right");
                telemetry.update();

                LeftF.setPower(-.2);
                LeftR.setPower(-.2);
                RightF.setPower(.2);
                RightR.setPower(.2);
                sleep(75);

                LeftF.setPower(1);
                LeftR.setPower(1);
                RightF.setPower(1);
                RightR.setPower(1);
                sleep(100);

                LeftF.setPower(.2);
                LeftR.setPower(.2);
                RightF.setPower(-.2);
                RightR.setPower(-.2);
                sleep(50);

                LeftF.setPower(1);
                LeftR.setPower(1);
                RightF.setPower(1);
                RightR.setPower(1);
                sleep(75);

                LeftF.setPower(.2);
                LeftR.setPower(.2);
                RightF.setPower(-.2);
                RightR.setPower(-.2);
                sleep(50);

                LeftF.setPower(1);
                LeftR.setPower(1);
                RightF.setPower(1);
                RightR.setPower(1);
                sleep(25 );


            }
            if (position == 2) {
                telemetry.addData("gold", "center");
                telemetry.update();

                LeftF.setPower(1);
                LeftR.setPower(1);
                RightF.setPower(1);
                RightR.setPower(1);
                sleep(200);

                LeftF.setPower(.2);
                LeftR.setPower(.2);
                RightF.setPower(-.2);
                RightR.setPower(-.2);
                sleep(300);

                LeftF.setPower(1);
                LeftR.setPower(1);
                RightF.setPower(1);
                RightR.setPower(1);
                sleep(200);

                LeftF.setPower(.2);
                LeftR.setPower(.2);
                RightF.setPower(-.2);
                RightR.setPower(-.2);
                sleep(100);


            }
            if (position == 3) {
                telemetry.addData("gold", "left");
                telemetry.update();

                LeftF.setPower(.2);
                LeftR.setPower(.2);
                RightF.setPower(-.2);
                RightR.setPower(-.2);
                sleep(75);

                LeftF.setPower(1);
                LeftR.setPower(1);
                RightF.setPower(1);
                RightR.setPower(1);
                sleep(200);

                LeftF.setPower(.2);
                LeftR.setPower(.2);
                RightF.setPower(-.2);
                RightR.setPower(-.2);
                sleep(150);

            }
            sleep(9999);
*/

            LeftF.setPower(1);
            LeftR.setPower(1);
            RightF.setPower(1);
            RightR.setPower(1);
            sleep(2500);

            LeftF.setPower(0);
            LeftR.setPower(0);
            RightF.setPower(0);
            RightR.setPower(0);


            LeftF.setPower(-1);
            LeftR.setPower(-1);
            RightF.setPower(1);
            RightR.setPower(1);
            sleep(3000);

         /*
            LeftF.setPower(1);
            LeftR.setPower(1);
            RightF.setPower(1);
            RightR.setPower(1);
            sleep(700);

            LeftF.setPower(.2);
            LeftR.setPower(.2);
            RightF.setPower(-.2);
            RightR.setPower(-.2);
            sleep(100);

            LeftF.setPower(0);
            LeftR.setPower(0);
            RightF.setPower(0);
            RightR.setPower(0);




//Throw Mineral
            intakeUp.setPower(-.7);
            sleep(2500);
            intakeUp.setPower(0);







            LeftF.setPower(.7);
            LeftR.setPower(.7);
            RightF.setPower(.7);
            RightR.setPower(.7);
            sleep(500);

            intakeUp.setPower(-.4);
            sleep(300);
            intakeUp.setPower(0);


            intakeIn.setPower(.3);
            sleep(2000);
*/

        }

    }
}




