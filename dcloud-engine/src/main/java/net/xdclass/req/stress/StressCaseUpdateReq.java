package net.xdclass.req.stress;

import lombok.Data;

import java.io.Serializable;


@Data
public class StressCaseUpdateReq implements Serializable {


    private Long id;


    private Long projectId;


    private Long moduleId;

    private Long environmentId;

    private String name;

    private String description;

    private String assertion;

    private String relation;

    private String stressSourceType;

    private String threadGroupConfig;

    private String jmxUrl;

    private String path;

    private String method;

    private String query;

    private String header;

    private String body;

    private String bodyType;

}
