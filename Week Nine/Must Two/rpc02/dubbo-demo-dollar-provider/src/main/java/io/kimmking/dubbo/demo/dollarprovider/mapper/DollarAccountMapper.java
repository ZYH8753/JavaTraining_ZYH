package io.kimmking.dubbo.demo.dollarprovider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kimmking.dubbo.demo.dollarprovider.entity.DollarAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DollarAccountMapper extends BaseMapper<DollarAccount> {
    DollarAccount selectByUserid(@Param("userid") long userid);

    int subPriceById(@Param("id") long id, @Param("price") int price);

    int updatePriceInt(@Param("userid") long userid, @Param("price") int price);
}
