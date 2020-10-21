package com.moti.service.impl;

import com.moti.entity.Tag;
import com.moti.mapper.TagMapper;
import com.moti.redis.TagKey;
import com.moti.service.BaseService;
import com.moti.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莫提
 * @ClassName TagServiceImpl
 * @Description (Tag)表服务实现类
 * @date 2020-09-02 20:10:48
 * @Version 1.0
 **/
@Service("tagService")
public class TagServiceImpl extends BaseService implements TagService {

    /**
     * @param tag 实例对象
     * @return 是否成功
     * @Description 添加Tag
     * @author 莫提
     * @date 2020-09-02 20:10:48
     */
    @Override
    public boolean insert(Tag tag) {
        if (tagMapper.insert(tag) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @param id 主键
     * @return 是否成功
     * @Description 删除Tag
     * @author 莫提
     * @date 2020-09-02 20:10:48
     */
    @Override
    public boolean deleteById(Integer id) {
        if (tagMapper.deleteById(id) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     */
    @Override
    public Tag queryById(Integer id) {
        Tag tag = null;
        if (redisService.exists(TagKey.getById,String.valueOf(id))){
            tag = redisService.get(TagKey.getById, String.valueOf(id), Tag.class);
        }else {
            tag = tagMapper.queryById(id);
            if (!ObjectUtils.isEmpty(tag)){
                redisService.set(TagKey.getById,String.valueOf(id),tag,60*60*24);
            }
        }
        return tag;
    }

    /**
     * @return 对象列表
     * @Description 查询全部数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * 分页使用MyBatis的插件实现
     */
    @Override
    public List<Tag> queryAll() {
        List<Tag> tags = null;
        // 获取所有标签
        if (redisService.exists(TagKey.getIndex,"")){
            tags = redisService.getList(TagKey.getIndex,"",Tag.class);
        }else {
            // 获取并存入缓存
            tags = tagMapper.queryAll();
            tags.forEach(tag -> {
                tag.setArticleCount(articleTagMapper.getArticleCount(tag.getId()));
            });
            if (!ObjectUtils.isEmpty(tags)){
                redisService.setList(TagKey.getIndex,"",tags);
            }
        }
        return tags;
    }

    /**
     * @param tag 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     */
    @Override
    public List<Tag> queryAll(Tag tag) {
        return tagMapper.queryAll(tag);
    }

    /**
     * @param tag 实例对象
     * @return 是否成功
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-02 20:10:48
     */
    @Override
    public boolean update(Tag tag) {
        if (tagMapper.update(tag) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @return java.util.List<com.moti.entity.Tag>
     * @Description 根据文章ID查询所有标签
     * @Author 莫提
     * @Date 20:28 2020/9/2
     * @Param [id]
     **/
    @Override
    public List<Tag> queryByArticleId(Integer id) {
        return articleTagMapper.queryByArticleId(id);
    }

    /**
     * @return java.lang.Integer
     * @Description 根据标签ID获取关联文章的数量
     * @Author 莫提
     * @Date 10:10 2020/9/9
     * @Param [id]
     **/
    @Override
    public Integer getArticleCount(Integer id) {
        return articleTagMapper.getArticleCount(id);
    }

    /**
     * @return java.lang.Integer
     * @Description 获取评论的数量
     * @Author 莫提
     * @Date 18:00 2020/9/23
     * @Param []
     **/
    @Override
    public Integer getCount() {
        return tagMapper.getCount();
    }

}