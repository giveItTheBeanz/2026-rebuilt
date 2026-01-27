/*
 * Copyright (C) 2026 Team 3602 All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found
 * in the root directory of this project.
 */

package frc.team3602.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.team3602.robot.Constants.ShooterConstants;
import frc.team3602.robot.Constants.spindexerConstants;
import frc.team3602.robot.subsystems.CommandSwerveDrivetrain;
import frc.team3602.robot.subsystems.IntakeSubsystem;
import frc.team3602.robot.subsystems.ShooterSubsystem;
import frc.team3602.robot.subsystems.SpindexerSubsystem;
import frc.team3602.robot.subsystems.TurretSubsystem;

public class Superstructure {
    
    public IntakeSubsystem intakeSubsys;
    public ShooterSubsystem shooterSubsys;
    public SpindexerSubsystem spindexerSubsys;
    public TurretSubsystem turretSubsys;
    public CommandSwerveDrivetrain commandSwerveDrivetrainsubsys;

     public Superstructure(IntakeSubsystem IntakeSubsys, ShooterSubsystem ShooterSubsys, SpindexerSubsystem spindexerSubsys,
            TurretSubsystem TurretSubsys, CommandSwerveDrivetrain commandSwerveDrivetrainSubsys) {
        this.intakeSubsys = IntakeSubsys;
        this.shooterSubsys = ShooterSubsys;
        this.spindexerSubsys = spindexerSubsys;
        this.turretSubsys = turretSubsys;
            }

            //Intake
         public Command IntakeBall() {
        return intakeSubsys.setIntakeSpeed().withTimeout(0.2);}
         
        public Command OutakeBall() {
            return intakeSubsys.reverseIntake().withTimeout(.2);}

            //Shooter
        public Command ShootBall() {
            return shooterSubsys.setShootSpeed(ShooterConstants.kFeederMotorSpeed).withTimeout(1);
        }
        public Command FeedBall() {
            return shooterSubsys.setFeederSpeed(ShooterConstants.kFeederMotorSpeed).withTimeout(1);
        }

        //Turret

        //Spindexer
        public Command RunSpindexer() {
            return spindexerSubsys.setSpindexerSpeed(spindexerConstants.kSpindexerMotorSpeed).withTimeout(1);
        }

        public Command ReceiveBall() {
            return spindexerSubsys.setSpindexerSpeed(spindexerConstants.kRecieveFuelSpeed).withTimeout(1);
        }
    }

