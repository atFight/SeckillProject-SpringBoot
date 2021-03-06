package com.zwt.dao;

import com.zwt.dataobject.UserDO;
import org.springframework.stereotype.Component;

@Component
public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    int insert(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    int insertSelective(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    UserDO selectByPrimaryKey(Integer id);

    UserDO selectByTelPhone(String telphone);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    int updateByPrimaryKeySelective(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    int updateByPrimaryKey(UserDO row);
}