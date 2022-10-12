package com.mgr.MgrSpringApp.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    //      //n.from_user_id,u.photo_id,u.user_name,n.id,n.message,n.date
    private Long userid;
    private Long photoId;
    private String userName;
    private Long notificationId;
    private String notificationMessage;
    private Date date;
}
