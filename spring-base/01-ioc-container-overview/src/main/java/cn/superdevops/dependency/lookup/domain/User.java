package cn.superdevops.dependency.lookup.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 用户模型
 * @author: superdevops
 * @time: 2022/1/1 10:57
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    private Long id;
    private String name;
}
