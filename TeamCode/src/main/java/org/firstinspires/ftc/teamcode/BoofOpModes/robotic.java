package org.firstinspires.ftc.teamcode.BoofOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import java.util.List;

    public class robotic extends LinearOpMode {
        DcMotor LeftF, LeftR, RightF, RightR, Lift, Lift2, IntakeIn, IntakeUp;
        Servo Markermarker; //marker servo
        Servo Booper; //front push servo
        Servo intakeRotation; //intake servo

        private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
        private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
        private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
        private static final String VUFORIA_KEY = "AYWilRL/////AAABmf7FFVPzZUkeobQQQGUbh1EDt8vYvnEgdvhH/pgFaw+cAntRcDT0A2P+ANN0kBTBRbL0TkkveZytIzkczswGdS/Bd4DqbEGVzXRSpO4CGWRgqzrUcbIkJc2MengACTvq5PfpACG2aJwbiK/Y6ff6hMFzeqlJOs5B1Rx8gzOa4vI04GYY2QMChWvtr+uBKbToGEwrDUPf5hcJKxZraeu3tmw+GjKw+G5I0poG2Eu1D3vyKzzFwNZQFkto5GiI6t6JI2HwX/IMwoWlkNx3+t00cCvV6pNp5kuJ04gYEAb4zMwol1FiMLMytUX2iYvXMuz4kq/uC9ZAmUSZu9ARPcBpI9V0gUU9QbsDU1h8pzxqtoJu";

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

        public void init(int zed) {

            initVuforia();
            initTfod();


            LeftR = hardwareMap.get(DcMotor.class, "left_r");
            LeftF = hardwareMap.get(DcMotor.class, "left_f");
            RightR = hardwareMap.get(DcMotor.class, "right_r");
            RightF = hardwareMap.get(DcMotor.class, "right_f");
            Lift = hardwareMap.get(DcMotor.class, "lift");
            Lift2 = hardwareMap.get(DcMotor.class, "lift2");
            IntakeIn = hardwareMap.get(DcMotor.class, "intake_in");
            IntakeUp = hardwareMap.get(DcMotor.class,"intake_up");


            Markermarker = hardwareMap.get(Servo.class, "marcus");
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
            IntakeIn.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            IntakeUp.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            IntakeUp.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }


        private void initVuforia() {
            /*
             * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
             */
            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

            parameters.vuforiaLicenseKey = VUFORIA_KEY;
            parameters.cameraDirection = CameraDirection.BACK;

            //  Instantiate the Vuforia engine
            vuforia = ClassFactory.getInstance().createVuforia(parameters);

            // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
        }

        /**
         * Initialize the Tensor Flow Object Detection engine.
         */
        private void initTfod() {
            int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                    "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
        }

        public int position() {
            int i = 0;
            if (opModeIsActive()) {
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
                                        return 3;
                                    } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                        return 1;
                                    } else if(goldMineralX > silverMineral1X && goldMineralX < silverMineral2X){
                                        return 2;
                                    }
                                    else if(i>3500)
                                        break;
                                    else {
                                        sleep(1);
                                        i++;
                                        telemetry.addData("VuMark", "not visible");
                                        telemetry.update();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return 4;
        }

        public void leftPower(double power) {
            LeftR.setPower(-power);
            LeftF.setPower(power);
        }

        public void rightPower(double power) {
            RightF.setPower(power);
            RightR.setPower(-power);
        }

        public void strafeRight(double power) {
            LeftF.setPower(power);
            LeftR.setPower(-power);
            RightF.setPower(-power);
            RightR.setPower(power);
        }

        public void strafeLeft(double power) {
            LeftF.setPower(power);
            LeftR.setPower(power);
            RightF.setPower(power);
            RightR.setPower(-power);
        }

        public void forward(double power) {
            leftPower(-power);
            rightPower(-power);
        }

        public void right(double power) {
            leftPower(power);
            rightPower(-power);
        }
        public void left(double power) {
            leftPower(-power);
            rightPower(power);
        }

        public void hang (double power) {
            Lift.setPower(power);
            Lift2.setPower(power);
        }

        public void down(double power, int mm) {
            int pos = Lift.getCurrentPosition();

            if (power >= 0) {
                while (Lift.getCurrentPosition() > (pos - mmtoticks(Math.abs(mm)))) {
                    forward(power);
                }
            } else {
                while (Lift.getCurrentPosition() < (pos + mmtoticks(Math.abs(mm)))) {
                    forward(power);
                }
            }

            hang(0);
        }

        //Goes forward a specific amount
        public void forward(double power, int mm) {
            int pos = LeftF.getCurrentPosition();
            //Forward 1 meter to test encoders once we get the chance to use them

            if (power >= 0) {
                while (LeftF.getCurrentPosition() > (pos - mmtoticks(Math.abs(mm)))) {
                    forward(power);
                }
            } else {
                while (LeftF.getCurrentPosition() < (pos + mmtoticks(Math.abs(mm)))) {
                    forward(power);
                }
            }

            forward(0);
        }

        //Turns a specific angle
        //Positive Degrees to the right, negative to the left
        public void turn(double power, int degrees) {
            double diameter = 245 * 2; //245 mm radius
            int pos = LeftF.getCurrentPosition();
            double Circumfrence = Math.PI * diameter;
            double distance = (Math.abs(degrees) * Circumfrence) /180;
            telemetry.addData("First one:", "");
            telemetry.addData("Motor Value:", LeftR.getCurrentPosition());
            telemetry.update();

            if (degrees >= 0) {
                telemetry.addData("Left one:", "");
                telemetry.addData("Motor Value:", LeftR.getCurrentPosition());
                telemetry.update();
                while (LeftF.getCurrentPosition() > (pos - mmtoticks(distance * 0.6))) {
                    left(Math.abs(power));
                    telemetry.addData("Left one cont:", "");
                    telemetry.addData("Motor Value:", LeftR.getCurrentPosition());
                    telemetry.update();
                }
            } else {
                telemetry.addData("Right one:", "");
                telemetry.addData("Motor Value:", LeftR.getCurrentPosition());
                telemetry.update();
                while (LeftF.getCurrentPosition() < (pos + mmtoticks(distance * 0.6))) {
                    telemetry.addData("Right one cont:", "");
                    telemetry.addData("Motor Value:", LeftR.getCurrentPosition());
                    telemetry.update();
                    right(Math.abs(power));
                }
            }

            forward(0);
        }

        //Positive power is to the right, negative to the left. Strafes a specific distance
        public void strafe(double power, int mm) {
            int pos = LeftF.getCurrentPosition();
            //Forward 1 meter to test encoders once we get the chance to use them

            //Strafe Right
            if (power >= 0) {
                while (LeftF.getCurrentPosition() > (pos - mmtoticks(Math.abs(mm)))) {
                    strafeRight(Math.abs(power));
                }
            }
            //Strafe left
            else {
                while (LeftF.getCurrentPosition() < (pos + mmtoticks(Math.abs(mm)))) {
                    strafeLeft(Math.abs(power));
                }
            }
            forward(0);
        }

        public double tickstomm(int ticks) {

            double mm = (ticks * (2));
            return mm;
        }
        //wheel gear ratio 1:2
        //Go forward for such and such distance
        public double mmtoticks(double mm)
        {
            double ticks = (mm / (2));
            return ticks;
        }

        public void telemetry ()
        {
            //get position
            telemetry.addData("Motor Positions", "");
            telemetry.addData("Right Back Motor Position: ", RightR.getCurrentPosition());
            telemetry.addData("Right Front Motor Position: ", RightF.getCurrentPosition());
            telemetry.addData("Left Back Motor Position: ", LeftR.getCurrentPosition());
            telemetry.addData("Left Front Motor Position: ", LeftF.getCurrentPosition());
            telemetry.addData("", "");
            telemetry.update();
        }


        public void runOpMode()
        {

        }
    }

