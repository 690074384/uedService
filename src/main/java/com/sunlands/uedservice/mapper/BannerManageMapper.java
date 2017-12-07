package com.sunlands.uedservice.mapper;

import com.sunlands.uedservice.po.BannerManage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author lvpenghui
 * @date 2017/12/6 12:21
 */
@Mapper
public interface BannerManageMapper {

    /**
     * 向tb_banner_manage表中插入一条记录
      * @param bannerManage
     */
    @Insert("insert into tb_banner_manage(id,picture_url,title,type,creator,updater,sequence) values(#{id},#{pictureUrl},#{title},#{type},#{creator},#{updater},#{sequence})")
    void insertOne(BannerManage bannerManage);

    /**
     * 根据id获取未被删除得单条记录
     * @param id
     * @return
     */
    @Select("select id,picture_url as pictureUrl,type,download_times as downloadTimes,creator,updater,create_time as createTime,update_time as updateTime,sequence,delete_flag as deleteFlag from tb_banner_manage where id = #{id} and delete_flag = 0")
    BannerManage selectById(Long id);

    /**
     * 查出tb_banner_manage中对应起始值记录
     * @param startNum
     * @param endNum
     * @return
     */
    @Select("select id,picture_url as pictureUrl,type,download_times as downloadTimes,creator,updater,create_time as createTime,update_time as updateTime,sequence,delete_flag as deleteFlag from tb_banner_manage where delete_flag = 0 order by sequence limit #{arg0},#{arg1}")
    List<BannerManage> getAllByPageNum(int startNum, int endNum);

    /**
     * 修改tb_banner_manage表中某一项对应删除状态位
     * @param id
     */
    @Update("update tb_banner_manage set delete_flag = 1 where id = #{id}")
    void updateDeleteFlagById(Long id);

    /**
     * 根据id获取tb_banner_manage中信息（包含已删除信息）
     * @param id
     * @return
     */
    @Select("select id,picture_url as pictureUrl,type,download_times as downloadTimes,creator,updater,create_time as createTime,update_time as updateTime,sequence,delete_flag as deleteFlag from tb_banner_manage where id = #{id}")
    BannerManage getById(Long id);
}