package io.kimmking.dubbo.demo.dollarprovider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kimmking.dubbo.demo.api.FreezeAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface FreezeMapper extends BaseMapper<FreezeAccount> {
}
