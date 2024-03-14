package ravenrobotics.defensebot;

public class Constants {
    public static class DSConstants {
        //The port for the drive controller.
        public static final int kDriverController = 0;

        //The slew rates for the inputs.
        public static final double kXSlewRate = 1.0;
        public static final double kZSlewRate = 1.0;
    }

    public static class DriveConstants {
        //The PWM ports for the motor controllers.
        public static final int kFrontLeft = 0;
        public static final int kFrontRight = 2;
        public static final int kBackLeft = 1;
        public static final int kBackRight = 3;
    }
}
