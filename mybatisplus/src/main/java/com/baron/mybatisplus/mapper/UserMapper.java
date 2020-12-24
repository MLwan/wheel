package com.baron.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baron.mybatisplus.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * @Description 复杂sql例子
     * @author zhanwang
     * @date 2020-12-14 15:31
     **/
    @Select("select tt.companycname,count(tt.insure_number) as taskNum,sum(tt.wcs) as farmerNum,sum(tt.zcs) as nFarmerNum,sum(tt.ycs) as unFarmerNum " +
            "from (select gg.companycname, " +
            "insure_number, " +
            "sum((case " +
            "when state in ('2', '3') then " +
            "1 " +
            "else " +
            "0 " +
            "end)) wcs, " +
            "count(1) zs, " +
            "sum((case " +
            "when state in ('3') then " +
            "1 " +
            "else " +
            "0 " +
            "end)) zcs, " +
            "sum((case " +
            "when state in ('2') then " +
            "1 " +
            "else " +
            "0 " +
            "end)) ycs " +
            "from standard_sample_point sp " +
            "left join ggcompany  gg on  sp.company_code=gg.companycode " +
            "where  FROM_UNIXTIME(standard_last_time) >subdate(now(),interval 24 hour) " +
            "and sp.is_deleted=0 " +
            "group by gg.companycname, insure_number) tt " +
            "where round((tt.wcs/tt.zs),2)>=0.05 " +
            "group by tt.companycname " )
    List<Map<String,Object>> selectCompletedStandardSamplePoint();

}
