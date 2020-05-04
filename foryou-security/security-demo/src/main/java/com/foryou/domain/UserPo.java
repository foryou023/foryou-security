package com.foryou.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.foryou.constraint.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

public class UserPo {

    /**
     * 简单视图
     */
    public interface UserSimpleView {};
    /**
     * 详细视图
     */
    public interface UserDetailsView extends UserSimpleView{};

    private String id;
    @MyConstraint(message = "自定义的校验逻辑")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Past(message = "生日不能是未来的时间")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailsView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserPo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
