package com.simpletech.wifisurfing.mapper.api;

import com.simpletech.wifisurfing.model.Login;
import com.simpletech.wifisurfing.model.Visit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
public interface TrackerMapper {

    /**
     * 插入一条新Login数据
     * @param model 添加的数据
     * @return 改变的行数
     */
    @Insert("INSERT INTO login ( id , idshop , idwifi , appid , openid , wxshopid , auth_type , mac_device , time_from_last , is_new_user , end_brand , time_local , create_time , update_time ) VALUES ( #{id} , #{idshop} , #{idwifi} , #{appid} , #{openid} , #{wxshopid} , #{authType} , #{macDevice} , #{timeFromLast} , #{isNewUser} , #{endBrand} , #{timeLocal} , #{createTime} , #{updateTime} )")
    int insertLogin(Login model);

    /**
     * 插入一条新visit数据
     * @param visit 添加的数据
     * @return 改变的行数
     */
    @Insert("INSERT INTO visit ( id , idshop , idwifi , mac_device , time_entry , time_leave , time_duration , time_from_last , is_new_user , count_logs , end_brand , create_time , update_time ) VALUES ( #{id} , #{idshop} , #{idwifi} , #{macDevice} , #{timeEntry} , #{timeLeave} , #{timeDuration} , #{timeFromLast} , #{isNewUser} , #{countLogs} , #{endBrand} , #{createTime} , #{updateTime} )")
    int insertVisit(Visit visit);

    /**
     * LOG更新visit数据
     * @param visit 添加的数据
     * @return 改变的行数
     */
    @Update("UPDATE visit SET time_leave=#{timeLeave} , time_duration=#{timeDuration} , count_logs=#{countLogs} , update_time=#{updateTime} WHERE id=#{id} ")
    int updateVisitByLog(Visit visit);

    /**
     * 根据店铺ID和mac获取上一次 Login
     * @param idshop 店铺ID
     * @param mac mac地址
     */
    @Select("SELECT id , idshop , idwifi , appid , openid , wxshopid , auth_type authType , mac_device macDevice , time_from_last timeFromLast , is_new_user isNewUser , end_brand endBrand , time_local timeLocal , create_time createTime , update_time updateTime FROM login WHERE idshop=#{idshop} AND mac_device=#{mac} ORDER BY create_time DESC LIMIT 0,1 ")
    Login findLastLoginByMacAndShop(@Param("idshop") String idshop, @Param("mac") String mac);

    /**
     * 根据店铺ID和mac获取上一次 Visit
     * @param idshop 店铺ID
     * @param mac mac地址
     */
    @Select("SELECT id , idshop , idwifi , mac_device macDevice , time_entry timeEntry , time_leave timeLeave , time_duration timeDuration , is_new_user isNewUser , count_logs countLogs , create_time createTime , update_time updateTime FROM visit WHERE idshop=#{idshop} AND mac_device=#{mac} ORDER BY create_time DESC LIMIT 0,1 ")
    Visit findLastVisitByMacAndShop(@Param("idshop") String idshop, @Param("mac") String mac);
}
