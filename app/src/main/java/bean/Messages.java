package bean;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class Messages {
    private String user_phone;

    public Messages(String user_phone) {
        this.user_phone = user_phone;
    }

    public Messages() {
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "user_phone='" + user_phone + '\'' +
                '}';
    }
}
