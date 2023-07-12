package com.xowen.mapper;

import com.xowen.pojo.EnglishUser;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Mapper // 在运行时，会自动的生成该接口的实现类对象(代理对象)，并且将该对象交给IOC容器管理
public interface UserMapper {

    // 查询全部用户信息
    @Select("select * from user_admin")
    public List<EnglishUser> list();

    // 删除指定id用户
    @Delete("delete from user_admin where id = #{id}")
    public void deleteUser(Integer id);

    // 插入用户
    @Options(keyProperty = "id", useGeneratedKeys = true) // 插入时，将自增id赋值给englishuser id，这样就不用异步等待插入完成后，再去获取数据库生成的自增id，估计它底层也是通过异步的方式获取，只不过以同步的形式展出
    @Insert("insert into user_admin(username, password, role, authrioty, email, phone, create_time) values(#{username},#{password},#{role},#{authrioty},#{email},#{phone},#{createTime})")
    public void insertUser(EnglishUser englishUser);

    // 更新用户
    @Update("update user_admin set phone = #{phone} where id = #{id}")
    public void updateUser(EnglishUser englishUser);

    // 查询用户
    @Select("select * from user_admin where create_time >= #{createTime}")
    public List<EnglishUser> getUserById(LocalDateTime  createTime);

    public List<EnglishUser> selectUserByXml(String role, short authrioty, LocalDateTime begin, LocalDateTime end);

    // 批量删除用户
    @Transactional(rollbackFor = Exception.class)  // 所有异常都回滚。默认只有Runtime异常会回滚
    public void deleteUserByIdsWithXml(List<Integer> ids);

    @Select("select * from user_admin where username = #{username} and password = #{password}")
    public EnglishUser login(EnglishUser englishUser);
}
