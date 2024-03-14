package ravenrobotics.defensebot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ravenrobotics.defensebot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    //The PWMTalonSRX objects so we can access and control the drive motors.
    private final PWMTalonSRX frontLeft = new PWMTalonSRX(DriveConstants.kFrontLeft);
    private final PWMTalonSRX frontRight = new PWMTalonSRX(DriveConstants.kFrontRight);
    private final PWMTalonSRX backLeft = new PWMTalonSRX(DriveConstants.kBackLeft);
    private final PWMTalonSRX backRight = new PWMTalonSRX(DriveConstants.kBackRight);
    
    //The DifferentialDrive object so we can automatically control the motors off of inputs.
    private final DifferentialDrive driveTrain = new DifferentialDrive(frontLeft, frontRight);

    //The static instance object so we can have getInstance().
    private static DriveSubsystem instance;

    /**
     * Returns the active instance of the DriveSubsystem.
     * 
     * @return The active DriveSubsystem instance.
     */
    public static DriveSubsystem getInstance() {
        //If the instance object created earlier is null, create it.
        if (instance == null) instance = new DriveSubsystem();
        //Return the instance object for use.
        return instance;
    }

    private DriveSubsystem() {
        //Configure the motors for use.
        configMotors();
    }

    /**
     * Drives the drivetrain.
     * 
     * @param forward The forward speed in a range of [-1.0, 1.0]
     * @param rotation The rotation speed in a range of [-1.0, 1.0]
     */
    public void drive(double forward, double rotation) {
        //Call the arcadeDrive method from the driveTrain object and pass the forward and rotation values.
        driveTrain.arcadeDrive(forward, rotation);
    }

    private void configMotors() {
        //Set the left side motors to be inverted, and the right side to not be inverted.
        frontLeft.setInverted(true);
        frontRight.setInverted(false);
        backLeft.setInverted(true);
        backRight.setInverted(false);

        //Add the back motors as "followers" to their respective front motors (when a input is given to the front, the back will also recieve that input).
        frontLeft.addFollower(backLeft);
        frontRight.addFollower(backRight);
    }
}
