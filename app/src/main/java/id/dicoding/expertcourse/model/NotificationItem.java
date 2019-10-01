package id.dicoding.expertcourse.model;

public class NotificationItem {
    public static final String REMINDER_NOTIFICATION_CHANNEL_ID = "reminder_notification";
    public static final String RELEASE_NOTIFICATION_CHANNEL_ID = "release_notification";
    //we dont have to differentiate reminder notifcation id because we dont need to create multiple notification on user device bar
    public static final int REMINDER_NOTIFICATION_ID = 100;
    public static final int RELEASE_NOTIFICATION_ID = 200;
    private int notifId;
    private String channelId;
    private String channelName;
    private String title;
    private String message;
    private int icon;

    public NotificationItem() {}

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getNotifId() {
        return notifId;
    }

    public void setNotifId(int notifId) {
        this.notifId = notifId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message != null ? message : "";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
