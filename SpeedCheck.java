import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avar12 on 23.01.2018.
 */
public class SpeedCheck {


    public String[] getTags(PlayerMoveData data) {

        boolean sprinting = data.isSprinting();

        boolean onground = data.getOnGround();

        boolean velocity = data.getVelocity();


        List<String> tags = new ArrayList<>();

        if (sprinting) {
            tags.add("sprint");
        }

        if (velocity) {
            tags.add("velocity");
        }

        //OnGround state
        if (onground) {

            double motionY = data.getMotionY();

            //Hovering slowly
            if (motionY == 0) {
                tags.add("hovering");
            }

            //Setting off
            if (motionY != 0) {

                //Jumping
                if (motionY > 0) {
                    tags.add("jumping");
                }

                //Falling off
                if (motionY < 0) {
                    tags.add("falling");
                }
            }

        }

        return (String[]) tags.toArray();
    }

    /**
     * @param data
     * @param horizontalLimit
     * @param verticalLimit
     * @return two doubles, first is the new horizontal speed limit, second the new vertical limit
     */

    public double[] adjustSpeedLimit(PlayerMoveData data, double horizontalLimit, double verticalLimit) {

        //First double returned
        double finalHLimit = horizontalLimit;

        //Second double returned
        double finalVLimit = verticalLimit;

        String[] tags = getTags(data);

        boolean velocity = containsString(tags, "velocity");
        boolean jumping = containsString(tags, "jumping");
        boolean onground = data.getOnGround();


        //Handle the velocity which gets sent by the server and affects the players motion
        if (velocity) {

            /*
             * The server sends a velocity vector to the player, the player will respond by adding the velocity vectors components to the players motion
             * So the speed limits should be adjusted by the vectors horizontal and vertical length
             */
            double vX = data.getVelocityX();
            double vY = data.getVelocityY();
            double vZ = data.getVelocityZ();

            double hLength = Math.sqrt(Math.pow(vX, 2) + Math.pow(vZ, 2));
            double vLength = Math.abs(vY);

            //Adjust the speed limit
            //horitonzal
            finalHLimit += hLength;

            //vertical
            finalVLimit += vLength;

        }


        //If the player is jumping, he will accelerate so the speed limit needs to be adjusted
        if (jumping) {

            double motionY = data.getMotionY();

            //Taken from mc-code
            double var1 = data.getYaw() * 0.017453292F;
            double extraSpeedJumpX = Math.sin(var1) * 0.2F;
            double extraSpeedJumpZ = Math.cos(var1) * 0.2F;
            //Vectorial length of the new x and z speed adjustments
            double extraJumpSpeed = Math.sqrt(Math.pow(extraSpeedJumpX, 2) + Math.pow(extraSpeedJumpZ, 2));

            finalHLimit += extraJumpSpeed * SpeedConstants.JUMP_ACC;


        }

        return new double[]{finalHLimit, finalVLimit};
    }


    public boolean containsString(String[] iArray, String keyword) {
        for (String string : iArray) {
            if (string.equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }

}
