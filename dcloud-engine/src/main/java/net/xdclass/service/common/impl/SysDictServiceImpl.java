package net.xdclass.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.common.SysDictDTO;
import net.xdclass.mapper.SysDictMapper;
import net.xdclass.model.SysDictDO;
import net.xdclass.service.common.SysDictService;
import net.xdclass.util.SpringBeanUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 **/
@Service
@Slf4j
public class SysDictServiceImpl implements SysDictService {

    @Resource
    private SysDictMapper sysDictMapper;


    @Override
    public Map<String, List<SysDictDTO>> listByCategory(String[] category) {
        Map<String, List<SysDictDTO>> map = new HashMap<>(category.length);

        for(String item: category){
            LambdaQueryWrapper<SysDictDO> queryWrapper = new LambdaQueryWrapper<>(SysDictDO.class);
            queryWrapper.eq(SysDictDO::getCategory, item);
            List<SysDictDO> sysDictDOList = sysDictMapper.selectList(queryWrapper);
            List<SysDictDTO> sysDictDTOList = SpringBeanUtil.copyProperties(sysDictDOList, SysDictDTO.class);
            map.put(item, sysDictDTOList);
        }
        return map;
    }
}
