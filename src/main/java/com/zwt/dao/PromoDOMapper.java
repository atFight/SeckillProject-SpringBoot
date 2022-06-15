package com.zwt.dao;

import com.zwt.dataobject.PromoDO;
import org.springframework.stereotype.Component;

@Component
public interface PromoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Wed Jun 15 14:33:16 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Wed Jun 15 14:33:16 CST 2022
     */
    int insert(PromoDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Wed Jun 15 14:33:16 CST 2022
     */
    int insertSelective(PromoDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Wed Jun 15 14:33:16 CST 2022
     */
    PromoDO selectByPrimaryKey(Integer id);

    PromoDO selectByItemId(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Wed Jun 15 14:33:16 CST 2022
     */
    int updateByPrimaryKeySelective(PromoDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Wed Jun 15 14:33:16 CST 2022
     */
    int updateByPrimaryKey(PromoDO row);
}