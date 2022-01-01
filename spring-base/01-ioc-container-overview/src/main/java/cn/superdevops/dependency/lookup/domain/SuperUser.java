package cn.superdevops.dependency.lookup.domain;

import cn.superdevops.dependency.lookup.annotation.Super;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: 超级用户
 * @author: superdevops
 * @time: 2022/1/1 18:00
 */
@Getter
@Setter
@ToString(callSuper = true)
@Super
public class SuperUser extends User {
    private String address;
}
