package com.jeyson.tools.lambda;

/**
 * @Author: liujishuai
 * @Time:
 * @Description:
 */
public class HelloDTO {
    private Long id;
    private int status;
    private String name;
    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HelloDTO(Long id, int status, String name, String desc) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.desc = desc;
    }


    @Override
    public String toString() {
        return "HelloDTO{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
