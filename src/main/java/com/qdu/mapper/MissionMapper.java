package com.qdu.mapper;

import com.qdu.beans.CUser;
import com.qdu.beans.CUserMission;
import com.qdu.beans.Mission;
import com.qdu.beans.Sup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mission record);

    int insertSelective(Mission record);

    Mission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mission record);

    int updateByPrimaryKey(Mission record);

	List<Mission> query(@Param("mname") String mname, @Param("qzhj") String qzhj,@Param("mstatus") Integer mstatus);

	Mission getBcById(@Param("id") Integer id);

	int update(Mission Mission);

	int remove(@Param("ids") List<Integer> ids);

	List<Mission> getModel();

	void jian(@Param("gno") String gno);

	void jia(@Param("gno") String gno);

	List<Mission> getBcs(@Param("ids") List<Integer> ids);

    List<Sup> getSup();

	List<CUser> getMan();

	int insertCUM(CUserMission record);

	CUser selectByName(@Param("name")String name);

	void doRemove(@Param("mid")Integer mid);

	void doUpdate(CUserMission cUserMission);

	void doUpdate2(Mission mission);

}