package com.zwt.dao;

import com.zwt.dataobject.ItemDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    int insert(ItemDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    int insertSelective(ItemDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    ItemDO selectByPrimaryKey(Integer id);

    List<ItemDO> listItem();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    int updateByPrimaryKeySelective(ItemDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jun 14 15:03:32 CST 2022
     */
    int updateByPrimaryKey(ItemDO row);

    int increaseSales(@Param("itemId") Integer itemId, @Param("amount") Integer amount);
}