/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.cscore.CvSink;
//import edu.wpi.cscore.CvSource;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.AnalogGyro;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
private Solenoid s1,s2;
Compressor C = new Compressor(1);
//Compressor.start ();
//void frc.robot.Robot.solenoid(1) = new solenoid(0);

public void autonomous() {
}
public void operator()
{}

//Motor Controllers;
Spark left = new Spark(0);          
Spark right = new Spark(1);           

PWMTalonSRX lift = new PWMTalonSRX(3);   
PWMTalonSRX intake = new PWMTalonSRX(2); 

//MISC Devices;
DoubleSolenoid hatch = new DoubleSolenoid(1,0,1); //Omnni solenoid

Encoder encLeft = new Encoder(0,1, true, Encoder.EncodingType.k2X);  //needs DIO number(s)
Encoder encRight = new Encoder(2,3, true, Encoder.EncodingType.k2X); //needs DIO number(s)
Encoder encLift = new Encoder(4,5, true, Encoder.EncodingType.k2X);  //needs DIO number(s)

// AnalogGyro gyro1 = new AnalogGyro (0);

//Drive Systems;
	DifferentialDrive myDrive = new DifferentialDrive(left, right);
	Joystick joy1 = new Joystick(0);
	Joystick joy2 = new Joystick(1);
	DriverStation fms = DriverStation.getInstance();

//.GlobalUtilities/Variables;

	public void robotInit() {
		//Gyro Setup
		CameraServer.getInstance().startAutomaticCapture();
	}
	
	public void autonomousInit() {
		
	}
  
  
  // put auton code
 

	public void autonomousPeriodic() {
		teleopPeriodic();
		/*myDrive.arcadeDrive((Math.pow(joy1.getRawAxis(1),3.0) * 0.7), (Math.pow(-joy1.getRawAxis(4),3.0)) * 0.82);
		if (joy2.getRawButton(6) == true) {
			s1.set(false);
			s2.set(true);
		}else{
			s1.set(true);
			s2.set(false);
    		if (joy2.getRawButton(2) == true) {
	    		intake.set(.50);
    		}else{
	    		intake.set(0);

	    		if (joy2.getRawButton(3) == true) {
		    		intake.set(-.50);
	    		}else{
		    		intake.set(0);

	            	//if (joy2.getRawButton(2) == true) {
		              //  lift.setInverted(true);

	                	if (joy2.getRawButton(/*put button number here4) == true) {
		                	lift.set(.5);
	            	    }else{
		                 	lift.set(0);

             	        	if (joy2.getRawButton(1) == true) {
		                    	lift.set(-.5);
	                     	}else{
		                     	lift.set(0);

                         		if (joy2.getRawButton(/*put button number here4) == true) {
	                            	lift.set(-.5);
                        		}else{
	                            	lift.set(0);
                            		if (encLift.getDistance() >= 2000 == true) {
                    	            	lift.set(-5);
                            		}
	                            }

              	            }
     	            	}
	                }
            	}
	    	}
    	}*/

	}

	public void teleopInit() {
	}

	boolean hatchPressed = false;
	boolean hatchDeployed = false;

	public void teleopPeriodic() {

		//Joystick (1);
	    // 		myDrive.arcadeDrive(joy1.getRawAxis(1), -joy1.getRawAxis(4) ); // original code - too responsive/too much power
		myDrive.arcadeDrive((Math.pow(joy1.getRawAxis(1),3.0) * 0.7), (Math.pow(-joy1.getRawAxis(4),3.0)) * 0.85);

		if (joy2.getRawButton(1)) {

			if (!hatchPressed) {
				hatchPressed = true;
				// do something
				if (hatchDeployed) {
					hatch.set(Value.kReverse);
					hatchDeployed = false;
				} else {
					hatch.set(Value.kForward);
					hatchDeployed = true;
				}
			}
		} else {
			hatchPressed = false;
		}

		if (joy2.getPOV() == 0) {
			intake.set(0.7);
		} else if (joy2.getPOV() == 180) {
			intake.set(-0.7);
		} else {
			intake.set(0);
		}
		
     	//if (joy1.getRawButton(11)) {
	   	//	encLeft.reset();
	    //		encRight.reset();
		//}
		
		if(Math.abs(joy2.getRawAxis(1)) > 0.1){
		    lift.set(0.4*Math.pow(-joy2.getRawAxis(1), 3.0));
		} else {
			lift.set(0.0);
		}
	}
	
	void hideme() {
		//Joystick 2;
		if (joy2.getRawButton(6) == true) {
			s1.set(false);
			s2.set(true);
		}else{
			s1.set(true);
			s2.set(false);
    		if (joy2.getRawButton(2) == true) {
	    		intake.set(.50);
    		}else{
	    		intake.set(0);

	    		if (joy2.getRawButton(3) == true) {
		    		intake.set(-.50);
	    		}else{
		    		intake.set(0);

	            	if (joy2.getRawButton(/*put button number here*/2) == true) {
		                lift.setInverted(true);

	                	if (joy2.getRawButton(/*put button number here*/4) == true) {
		                	lift.set(.5);
	            	    }else{
		                 	lift.set(0);

             	        	if (joy2.getRawButton(1) == true) {
		                    	lift.set(-.5);
	                     	}else{
		                     	lift.set(0);

                         		if (joy2.getRawButton(/*put button number here*/4) == true) {
	                            	lift.set(-.5);
                        		}else{
	                            	lift.set(0);
                            		if (encLift.getDistance() >= 2000 == true) {
                    	            	lift.set(-5);
                            		}
	                            }

              	            }
     	            	}
	                }
            	}
	    	}
    	}
    }
}

	

 