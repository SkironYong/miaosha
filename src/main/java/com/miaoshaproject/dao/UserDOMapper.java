package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Mar 01 20:58:35 GMT+08:00 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Mar 01 20:58:35 GMT+08:00 2020
     */
    int insert(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Mar 01 20:58:35 GMT+08:00 2020
     */
    int insertSelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Mar 01 20:58:35 GMT+08:00 2020
     */
    UserDO selectByPrimaryKey(Integer id);

    UserDO selectByTelphone(String telphone);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Mar 01 20:58:35 GMT+08:00 2020
     */
    int updateByPrimaryKeySelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Mar 01 20:58:35 GMT+08:00 2020
     */
    int updateByPrimaryKey(UserDO record);
}