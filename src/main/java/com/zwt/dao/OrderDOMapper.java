package com.zwt.dao;

import com.zwt.dataobject.OrderDO;
import org.springframework.stereotype.Component;

@Component
public interface OrderDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Tue Jun 14 20:47:30 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Tue Jun 14 20:47:30 CST 2022
     */
    int insert(OrderDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Tue Jun 14 20:47:30 CST 2022
     */
    int insertSelective(OrderDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Tue Jun 14 20:47:30 CST 2022
     */
    OrderDO selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Tue Jun 14 20:47:30 CST 2022
     */
    int updateByPrimaryKeySelective(OrderDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Tue Jun 14 20:47:30 CST 2022
     */
    int updateByPrimaryKey(OrderDO row);
}