/**
 * Created by Avar12 on 23.01.2018.
 */
public class PlayerMoveData {

    //Basic states
    private boolean onGround;
    private boolean hasVelocity;


    private boolean sprinting;



    //Motions
    private double motionX;
    private double motionY;
    private double motionZ;

    private double velocityX;
    private double velocityY;
    private double velocityZ;

    //Additional
    private float yaw;
    private float pitch;

    public PlayerMoveData() {

        //Set the defaults, will be overwritten anyways
        onGround = true;
        hasVelocity = false;

        motionX = 0D;
        motionY = 0D;
        motionZ = 0D;

        yaw = 0F;
        pitch = 0F;
    }

    //Getters
    public boolean getOnGround() {
        return onGround;
    }

    public boolean getVelocity() {
        return hasVelocity;
    }
    public boolean isSprinting() {
        return sprinting;
    }

    public double getMotionX() {
        return motionX;
    }

    public double getMotionY() {
        return motionY;
    }

    public double getMotionZ() {
        return motionZ;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public double getVelocityZ() {
        return velocityZ;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    //Setters
    public void setOnGround(boolean onGround){
        this.onGround = onGround;
    }
    public void setVelocity(boolean velocity){
        hasVelocity = velocity;
    }
    public void setMotionX(double motionX) {
        this.motionX = motionX;
    }

    public void setMotionY(double motionY) {
        this.motionY = motionY;
    }

    public void setMotionZ(double motionZ) {
        this.motionZ = motionZ;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public void setVelocityZ(double velocityZ) {
        this.velocityZ = velocityZ;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }




}
