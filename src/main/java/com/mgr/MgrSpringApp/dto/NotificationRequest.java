package com.mgr.MgrSpringApp.dto;

import java.util.List;

import lombok.Data;

@Data
public class NotificationRequest 
{
    private String msg;
    private List<Long> toUserIds;

}
