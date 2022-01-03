package io.kimmking.dubbo.demo.dollarprovider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kimmking.dubbo.demo.api.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
