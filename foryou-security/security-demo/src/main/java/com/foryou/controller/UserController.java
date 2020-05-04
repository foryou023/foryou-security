package com.foryou.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.foryou.domain.UserPo;
import com.foryou.exception.UserNotExistException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 查询所有
     * @param username
     * @param pageable
     * @return
     */
    @GetMapping
    @JsonView(UserPo.UserSimpleView.class)
    public List<UserPo> query(@RequestParam(name = "username",required = false,defaultValue = "tom") String username,
                              @PageableDefault(size = 15,page = 2,sort = "username,asc") Pageable pageable){
        System.out.println(username);
        List<UserPo> list = new ArrayList<>();
        list.add(new UserPo());
        list.add(new UserPo());
        list.add(new UserPo());
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        Sort sort = pageable.getSort();
        System.out.println(pageNumber);
        System.out.println(pageSize);
        System.out.println(sort);
        return list;
    }

    /**
     * 查询详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @JsonView(UserPo.UserDetailsView.class)
    public UserPo getUser(@PathVariable("id") String id){
        System.out.println(id);
        UserPo userPo = new UserPo();
        userPo.setUsername("tom");
//        int num = 10/0;
        return userPo;
//        throw new UserNotExistException(id);
    }

    /**
     * 创建用户
     * @param userPo
     * @return
     */
    @PostMapping
    public UserPo createUser(@Valid @RequestBody UserPo userPo, BindingResult errors){
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error-> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(userPo);
        userPo.setId("1");
        return userPo;
    }


    /**
     * 更新用户
     * @param userPo
     * @param errors
     * @return
     */
    @PutMapping()
    public UserPo updateUserPo(@Valid @RequestBody UserPo userPo,BindingResult errors){
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error-> {
                /*FieldError fieldError = (FieldError) error;
                System.out.println(fieldError.getField()+":"+error.getDefaultMessage());*/
                System.out.println(error.getDefaultMessage());
            });
        }
        System.out.println(userPo);
        return userPo;
    }

    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id){
        System.out.println(id);
    }


    @GetMapping("/me")
    public Object getMe(Authentication authentication){
        return authentication;
    }


}
