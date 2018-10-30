package org.firstinspires.ftc.teamcode.newOpModes;

import lib.neo.bot.NeoBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class TestOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        NeoBot robot = new NeoBot(this);

        waitForStart();

        robot.driveTrain.go_fw(100, 1);
        robot.driveTrain.rot(90, 1);
    }
}
