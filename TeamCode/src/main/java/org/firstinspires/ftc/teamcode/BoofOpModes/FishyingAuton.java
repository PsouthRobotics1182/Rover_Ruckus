package org.firstinspires.ftc.teamcode.BoofOpModes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;


@Autonomous(name = "gudAutoFar?", group = "auto")
public class FishyingAuton extends robotic
{
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "AYWilRL/////AAABmf7FFVPzZUkeobQQQGUbh1EDt8vYvnEgdvhH/pgFaw+cAntRcDT0A2P+ANN0kBTBRbL0TkkveZytIzkczswGdS/Bd4DqbEGVzXRSpO4CGWRgqzrUcbIkJc2MengACTvq5PfpACG2aJwbiK/Y6ff6hMFzeqlJOs5B1Rx8gzOa4vI04GYY2QMChWvtr+uBKbToGEwrDUPf5hcJKxZraeu3tmw+GjKw+G5I0poG2Eu1D3vyKzzFwNZQFkto5GiI6t6JI2HwX/IMwoWlkNx3+t00cCvV6pNp5kuJ04gYEAb4zMwol1FiMLMytUX2iYvXMuz4kq/uC9ZAmUSZu9ARPcBpI9V0gUU9QbsDU1h8pzxqtoJu";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {


        waitForStart();

        /*
        lift down
        lift intake arm goes up
        take picture
        booper down
        mineral
        turn backwards and spit marker out back
        turn and crater
         */




        IntakeUp.setPower(1);
        sleep(300);
    //    down(1, (int) 25.4 * 4);

        //  sleep(3000);

        init(0);

        int position = position();

        if (position() == 1)
        {
            strafe(-0.3, (int) 25.4 * 10);
            forward(0.3, (int) 25.4 * 10);
            turn(-.3, 90);
        }
        else if (position() == 2)
        {
            forward(0.3, (int) 25.4 * 10);
            turn(-.3, 90);
            forward(0.3, (int) 25.4 * 10);
        }
        else if(position() == 3)
        {
            strafe(0.3, (int) 25.4 * 10);
            forward(0.3, (int) 25.4 * 10);
            turn(-.3, 90);
            forward(0.3, (int) 25.4 * 20);
        }
        else
        {
            forward(0.3, (int) 25.4 * 5);
            turn(-.3, 90);
            forward(0.3, (int) 25.4 * 20);
        }

        sleep(5000);

        forward(0.3, (int) 25.4 * 15);
        turn(-.3, 45);
        forward(0.3, (int) 25.4 * 25);





        forward(-0.3, (int) 25.4 * 40);
    }
}

