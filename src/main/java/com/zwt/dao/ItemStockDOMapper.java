package com.zwt.dao;

import com.zwt.dataobject.ItemStockDO;
import org.springframework.stereotype.Component;

@Component
public interface ItemStockDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    int insert(ItemStockDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    int insertSelective(ItemStockDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    ItemStockDO selectByPrimaryKey(Integer id);

    ItemStockDO selectByItemId(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    int updateByPrimaryKeySelective(ItemStockDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    int updateByPrimaryKey(ItemStockDO row);
}