package com.zwt.dao;

import com.zwt.dataobject.UserPasswordDO;

public interface UserPasswordDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    int insert(UserPasswordDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    int insertSelective(UserPasswordDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    UserPasswordDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    int updateByPrimaryKeySelective(UserPasswordDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sun Jun 12 12:02:19 CST 2022
     */
    int updateByPrimaryKey(UserPasswordDO row);
}