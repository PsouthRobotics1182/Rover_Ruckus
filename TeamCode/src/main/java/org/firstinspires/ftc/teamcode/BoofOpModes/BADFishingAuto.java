package org.firstinspires.ftc.teamcode.BoofOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous (name = "FishingAuto")
public class BADFishingAuto extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY = "AcmoujL/////AAAAGfYN1VWHOESQp02jdVbkpgRIroIXb6bGJbVg+YmQNOR1Utps1uBrE31QT5LTDRtXTqfGsXa1UDAVYDCODNbSDvvBqaeL+InYfonHHdT5uSQCUlOM5KznGi0nxg87OadM5azVuy9kk+uc0w3lmN/8PDzgxO14VRINXAf3w5AkMzhZAhKbzOH3PXYD15b9WsxeBfgDLHahE3Utn1i5u4EYZwizxBCa2Kg4HvtuhNLPBW7qjAfU+VEEsXHXCsJXU16uPaSQoPGWQsgZF729eI7aKmFa/zImSqxi1LizI6Xx8GkLOINg9j+gOixUkF115rrI5Lg4in21bKiR51FR9WmTunV8e/gGPBPrcfGFRP77fzsa";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        tfod.activate();

        DcMotor LeftR; //left rear motor
        DcMotor LeftF; //left front motor
        DcMotor RightR; //right rear motor
        DcMotor RightF; //right front motor
        DcMotor Lift; //back lift motor
        DcMotor Lift2; //lift second motor (closest to lift)
        //DcMotor IntakeIn;//intake motor
        //DcMotor IntakeUp;//flywheel motor

        Servo Markermarker; //marker servo
        Servo Booper; //front push servo


        //discriminator = hardwareMap.get(ColorSensor.class, "colour");
        LeftR = hardwareMap.get(DcMotor.class, "left_r");
        LeftF = hardwareMap.get(DcMotor.class, "left_f");
        RightR = hardwareMap.get(DcMotor.class, "right_r");
        RightF = hardwareMap.get(DcMotor.class, "right_f");
        Lift = hardwareMap.get(DcMotor.class, "lift");
        Lift2 = hardwareMap.get(DcMotor.class, "lift2");
        //IntakeIn = hardwareMap.get(DcMotor.class, "intake_in");
        //IntakeUp = hardwareMap.get(DcMotor.class,"intake_up");


        Markermarker = hardwareMap.get(Servo.class, "marcus");
        Booper = hardwareMap.get(Servo.class, "boop");


        LeftR.setDirection(DcMotor.Direction.REVERSE);
        LeftF.setDirection(DcMotor.Direction.FORWARD);
        RightR.setDirection(DcMotor.Direction.REVERSE);
        RightF.setDirection(DcMotor.Direction.FORWARD);
        Lift.setDirection(DcMotor.Direction.FORWARD);
        Lift2.setDirection(DcMotor.Direction.FORWARD);
        //IntakeIn.setDirection(DcMotor.Direction.FORWARD);
        //IntakeUp.setDirection(DcMotor.Direction.FORWARD);


        LeftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //IntakeIn.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //IntakeUp.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Booper.setPosition(1);

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        waitForStart();

        Lift.setPower(1);
        sleep(1500);

        RightF.setPower(-.1);
        RightR.setPower(.1);
        LeftR.setPower(-.1);
        LeftF.setPower(.1);
        sleep(350);


        /** Activate Tensor Flow Object Detection. */
        if (tfod != null) {
            tfod.activate();
        }

        while (opModeIsActive()) {
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    telemetry.addData("# Object Detected", updatedRecognitions.size());
                    if (updatedRecognitions.size() == 3) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMineral2X = -1;
                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                goldMineralX = (int) recognition.getLeft();
                            } else if (silverMineral1X == -1) {
                                silverMineral1X = (int) recognition.getLeft();
                            } else {
                                silverMineral2X = (int) recognition.getLeft();
                            }
                        }
                        if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                            if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                telemetry.addData("Gold Mineral Position", "Left");
                            } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                telemetry.addData("Gold Mineral Position", "Right");
                            } else {
                                telemetry.addData("Gold Mineral Position", "Center");
                            }
                        }
                    }
                    telemetry.update();
                }
            }
        }
    }


    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */

    public String returnPosition() {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            tfod.activate();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }


        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() == 3) {
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }
                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            return "left";

                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            return "right";
                        } else {
                            return "center";
                        }

                    }
                }
            }
            return "idk you probably screwed up";
        }


        //public class initTfod {
        //int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
        //"tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        //TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        //tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        //tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
        //}


        return "idk you gay";
    }
}