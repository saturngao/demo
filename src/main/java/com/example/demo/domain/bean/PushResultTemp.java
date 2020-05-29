package com.example.demo.domain.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
//@Document(indexName = "pushresult", type = "pushresult", shards = 1, replicas = 0, refreshInterval = "-1")
public class PushResultTemp {
    private String id;

    private String msgName;

    private String msgDesc;

    private String userId;

    private String taskId;

    private String pushStatus;

    private Date createTime;

    private String paramValue;

    private String name;

    private String desc;

    private String pushRuleId;
}