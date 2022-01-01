package cn.superdevops.dependency.lookup.domain;

import cn.superdevops.dependency.lookup.annotation.Super;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: superdevops
 * @time: 2022/1/1 18:15
 */
@Super
@Getter
@Setter
@ToString(callSuper = true)
public class SuperMan extends User {
    private String superpower;
}
