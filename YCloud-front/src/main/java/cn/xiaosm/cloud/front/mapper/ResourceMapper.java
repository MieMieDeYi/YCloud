/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: RoleService
 * Author:   Young
 * Date:     2020/6/18 15:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.cloud.front.mapper;

import cn.xiaosm.cloud.front.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/18
 * @since 1.0.0
 */
@Repository
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 根据 hash 值获取文件名
     * @param hash
     * @return
     */
    @Select("SELECT `name` FROM `resource` WHERE `hash` = #{hash}")
    String selectNameByHash(String hash);

    /**
     * 根据 hash 值获取文件
     * @param hash
     * @return
     */
    @Select("SELECT * FROM `resource` WHERE `hash` = #{hash}")
    Resource selectByHash(String hash);

    @Select("SELECT * FROM `resource` WHERE `id` = #{resourceId} AND `parent_id` = #{parentId} AND `type` != 'dir'")
    Resource selectByParent(Long parentId, Integer resourceId);

    @Select("SELECT * FROM `resource` WHERE `id` = #{resourceId} AND `parent_id` = #{parentId} AND `type` != 'dir'")
    Resource selectByParentAndIdNotDir(Long parentId, Integer resourceId);

    @Select("SELECT * FROM `resource` WHERE `bucket_id` = #{bucketId} AND `parent_id` = #{parentId}")
    List<Resource> listRoot(int parentId, int bucketId);

    @Select("SELECT `id` FROM `resource` WHERE `bucket_id` = #{bucketId} AND `parent_id` = #{parentId} AND `name` = #{name} AND `type` = 'dir'")
    Long selectIdByBucketAndNameAndDir(int bucketId, Long parentId, String name);

    @Select("SELECT * FROM `resource` WHERE `parent_id` = #{parentId}")
    List<Resource> listByParentId(Long parentId);

    @Select("SELECT `id` FROM `resource` WHERE `parent_id` = #{parentId}")
    List<Long> listIdByParentId(Long parentId);

    @Select("SELECT * FROM `resource` WHERE `id` = #{id} AND `user_id` = #{userId}")
    Resource selectByIdAndUser(Long id, Long userId);

    List<Resource> selectByIdsAndUser(String ids, Long userId);

    @Select("SELECT count(id) FROM `resource` WHERE `hash` = #{hash}")
    int countByHash(String hash);

    @Select("SELECT * FROM `resource` WHERE `id` = #{id} AND `bucket_id` = #{bucketId}")
    Resource selectByIdAndBucket(int id, int bucketId);

    @Select("SELECT * FROM `share` s LEFT JOIN `resource` r ON s.id =  WHERE `id` = #{id} AND `bucket_id` = #{bucketId}")
    Resource selectByUUIDFromShare(String uuid, String shareId);

}