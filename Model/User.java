package Model;

/**
 * Created by Dennis on 03.11.2015.
 */
public class User {
    String userName;
    String realName;
    String location;
    String aboutMe;
    String profilePicture;
    boolean isRapper;
    Rapper rapper;

    public String getUserName(){
        return this.userName;
    }

    public String getRealName(){
        return this.realName;
    }

    public String getLocation(){
        return this.location;
    }

    public String getAboutMe(){
        return this.aboutMe;
    }

    public String getProfilePicture(){
        return this.profilePicture;
    }

    public boolean getIsRapper(){
        return this.isRapper;
    }

    public void setIsRapper(boolean isRapper){
        this.isRapper = isRapper;
    }

    public Rapper getRapper(){
        return this.rapper;
    }

    public User(String userName, String realName, String location, String aboutMe, String profilePicture, boolean isRapper, Rapper rapper){
        this.userName = userName;
        this.realName = realName;
        this.location = location;
        this.aboutMe = aboutMe;
        this.profilePicture = profilePicture;
        this.rapper = rapper;
        this.isRapper = isRapper;
    }
}
