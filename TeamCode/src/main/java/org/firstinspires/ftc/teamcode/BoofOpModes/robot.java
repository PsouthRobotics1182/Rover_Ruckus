package org.firstinspires.ftc.teamcode.BoofOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class robot {
    private DcMotor LeftR = null; //left rear motor
    private DcMotor LeftF = null; //left front motor
    private DcMotor RightR = null; //right rear motor
    private DcMotor RightF = null; //right front motor
    private DcMotor Lift = null; //back lift motor
    //private DcMotor Fly = null; //flywheel motor
    //private DcMotor Arm = null; //extending intake arm motor
    //private DcMotor Intake = null; //intake motor

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public void loop(){

    }
    public void init(HardwareMap ahwMap){



        hwMap = ahwMap;

        LeftR = hwMap.get(DcMotor.class, "left_r");
        LeftF = hwMap.get(DcMotor.class, "left_f");
        RightR = hwMap.get(DcMotor.class, "right_r");
        RightF = hwMap.get(DcMotor.class, "right_f");
        Lift = hwMap.get(DcMotor.class, "lift");
        //Fly = hwMap.get(DcMotor.class, "fly");
        //Arm = hwMap.get(DcMotor.class, "arm");
        //Intake = hwMap.get(DcMotor.class, "intake");

        LeftR.setDirection(DcMotor.Direction.REVERSE);
        LeftF.setDirection(DcMotor.Direction.FORWARD);
        RightR.setDirection(DcMotor.Direction.REVERSE);
        LeftF.setDirection(DcMotor.Direction.FORWARD);
        Lift.setDirection(DcMotor.Direction.FORWARD);

        LeftR.setPower(0);
        LeftF.setPower(0);
        RightR.setPower(0);
        RightF.setPower(0);
        Lift.setPower(0);

        LeftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        class FishMotor {

            private DcMotor motor;
            private int zeroPosition = 0;
            private DcMotor.RunMode mode = DcMotor.RunMode.RUN_USING_ENCODER;
            private LinearOpMode opMode;
            private String name;

            public FishMotor (LinearOpMode opMode, String name){
                this.opMode = opMode;
                this.name = name;
                motor = opMode.hardwareMap.get(DcMotor.class, name);
                motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

            public double getTicksPerRev() {return motor.getMotorType().getTicksPerRev();}

            public double getTicksPerSecond() {
                return motor.getMotorType().getAchieveableMaxTicksPerSecondRounded();
            }

            public void setPower(double power) {
                motor.setPower(power);
            }

            public double getPower() {
                return motor.getPower();
            }

            public void resetEncoder() {
                zeroPosition = motor.getCurrentPosition();
            }

            public int getCurrentPosition() {
                return motor.getCurrentPosition() - zeroPosition;
            }

            public int getAbsolutePosition() {
                return motor.getCurrentPosition();
            }

            public void setDirection(DcMotorSimple.Direction direction) {
                motor.setDirection(direction);
            }

            public void setMode(DcMotor.RunMode mode) {
                this.mode = mode;
                motor.setMode(mode);
            }

            public void addTelemetry() {
                opMode.telemetry.addData(name + "power", motor.getPower());
                opMode.telemetry.addData(name + "absolute position", getAbsolutePosition());
                opMode.telemetry.addData(name + "position", getCurrentPosition());
            }

            public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior){
                motor.setZeroPowerBehavior(behavior);
            }



        }




    }
                }

