package frc.team3602.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.team3602.robot.Constants.*;
import frc.team3602.robot.Vision;

public class TurretSubsystem extends SubsystemBase {


    //Motor
    private final TalonFX turretMotor = new TalonFX(TurretConstants.kTurretMotorID);

    public TurretSubsystem() {
        //Zero Encoder
        turretMotor.setPosition(0);

    }

    //Encoder
    public Double getEncoder() {
    return (turretMotor.getRotorPosition().getValueAsDouble() * 36.0);
    }

    //Vision
    public final Vision vision = new Vision();

    //Set Point *This number needs to be changed*
    public double setAngle = 90;

    //Controllers *These PID values need to be changed*
    private final PIDController turretController = new PIDController(1, 0.0, 0.0);

    //Commands
    public Command setAngle(double setAngle) {
        return runOnce(() -> {
            this.setAngle = setAngle;
        });
    }

    public Command testTurret(double voltage) {
        return runOnce(() -> {
            turretMotor.setVoltage(voltage);
        });
    }

    public Command stopTurret() {
        return runOnce(() -> {
            turretMotor.stopMotor();
        });
    }

    double rotationSpeed;

    //Calculations
        public double rAlignment() {
        
            double tx = vision.getTX();

            rotationSpeed = turretController.calculate(tx, 0);

            if (Math.abs(rotationSpeed) < 0.5) {
                rotationSpeed = 0;
            }

            return rotationSpeed;
        
        }

    //Periodic
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Turret Encoder", getEncoder());
    }   

    //Config
        private void configPivotSubsys() {

        // encoder configs
        var magnetSensorConfigs = new MagnetSensorConfigs();
        magnetSensorConfigs.AbsoluteSensorDiscontinuityPoint = 1;

        // Motor configs
        var motorConfigs = new MotorOutputConfigs();
        var limitConfigs = new CurrentLimitsConfigs();

        limitConfigs.StatorCurrentLimit = 30;
        limitConfigs.SupplyCurrentLimit = 30;
        limitConfigs.SupplyCurrentLimitEnable = true;
        limitConfigs.StatorCurrentLimitEnable = true;

        turretMotor.getConfigurator().apply(limitConfigs);

        motorConfigs.NeutralMode = NeutralModeValue.Coast;
        turretMotor.getConfigurator().apply(motorConfigs);
    } 
}