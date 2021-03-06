package girlsofsteel.tests;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import girlsofsteel.commands.CommandBase;

public class ShooterJags extends CommandBase {
    
    double speed = 0.0;

    int counter = 0;
    boolean pushed;
    double time;
    
    double WAIT_TIME = 1.0;
    
    public ShooterJags(){
        SmartDashboard.putBoolean("Shooter Jags", false);
        SmartDashboard.putNumber("Jag Speed", 0.0);
        SmartDashboard.putBoolean("Click When Done Testing Shooter Jags", false);
    }
    
    protected void initialize() {
        speed = SmartDashboard.getNumber("Jag Speed", 0.0);
        counter = 0;
        pushed = false;
        shooter.setJags(speed);
        time = timeSinceInitialized();
        while(timeSinceInitialized() - time < 4){
        }//overall wait time is 4 + WAIT_TIME = 5
        time = timeSinceInitialized();
    }

    protected void execute() {
        if(SmartDashboard.getBoolean("Shooter Jags", false)){
//            shooter.setJags(speed);
//            shooter.setShootTrue();
//        }else{
//            shooter.setJags(0.0);
//        }
        if(timeSinceInitialized() - time > WAIT_TIME){
            if(!pushed){
                feeder.pushShooter();
                pushed = true;
            }else{
                feeder.pullShooter();
                pushed = false;
//                counter++;
            }
            time = timeSinceInitialized();
        }
        }
    }

    protected boolean isFinished() {
        return SmartDashboard.getBoolean("Click When Done Testing Shooter Jags",
                false);
    }

    protected void end() {
        shooter.setJags(0.0);
    }

    protected void interrupted() {
        end();
    }
    
}
