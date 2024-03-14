package ravenrobotics.defensebot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import ravenrobotics.defensebot.Constants.DSConstants;
import ravenrobotics.defensebot.subsystems.DriveSubsystem;

public class DriveCommand extends Command {
    //The DriveSubsystem object so we can access and control the drivetrain.
    private final DriveSubsystem driveSubsystem;

    //The DoubleSupplier objects so we can get inputs from the controller.
    private final DoubleSupplier xSupplier, zSupplier;

    //The SlewRateLimiter objects so we can limit our acceleration on the drivetrain.
    private final SlewRateLimiter xLimiter, zLimiter;

    /**
     * Command to drive the robot based off of axis inputs.
     * 
     * @param xAxis The forward axis as a DoubleSupplier.
     * @param zAxis The rotation axis as a DoubleSupplier.
     */
    public DriveCommand(DoubleSupplier xAxis, DoubleSupplier zAxis) {
        //Get the active instance of the DriveSubsystem and use it for the local DriveSubsystem object.
        this.driveSubsystem = DriveSubsystem.getInstance();

        //Instantiate the DoubleSupplier axis from the arguments given.
        this.xSupplier = xAxis;
        this.zSupplier = zAxis;

        //Instantiate the SlewRateLimiters and use the slew rates from the Constants.java file.
        this.xLimiter = new SlewRateLimiter(DSConstants.kXSlewRate);
        this.zLimiter = new SlewRateLimiter(DSConstants.kZSlewRate);

        //Add the driveSubsystem as a requirement, so the CommandScheduler won't run multiple commands that use the subsystem at the same time.
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        //Create temporary variables to store the inputs to pass.
        double xSpeed, zSpeed;

        //Calculate the inputs based on the axis inputs and store them in the temporary variables.
        xSpeed = xLimiter.calculate(xSupplier.getAsDouble());
        zSpeed = zLimiter.calculate(zSupplier.getAsDouble());

        //Drive the drivetrain using the calculated inputs.
        driveSubsystem.drive(xSpeed, zSpeed);
    }
}
