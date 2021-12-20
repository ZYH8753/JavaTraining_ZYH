package com.zyh.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyh.server.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
