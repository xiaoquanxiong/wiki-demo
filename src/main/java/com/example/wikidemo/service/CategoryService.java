package com.example.wikidemo.service;


import com.example.wikidemo.domain.Category;
import com.example.wikidemo.domain.CategoryExample;
import com.example.wikidemo.mapper.CategoryMapper;
import com.example.wikidemo.req.CategoryQueryReq;
import com.example.wikidemo.req.CategorySaveReq;
import com.example.wikidemo.resp.CategoryQueryResp;
import com.example.wikidemo.resp.PageResp;
import com.example.wikidemo.utils.CopyUtil;
import com.example.wikidemo.utils.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<CategoryQueryResp> all(CategoryQueryReq categoryQueryReq) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        if(!ObjectUtils.isEmpty(categoryQueryReq.getName())){
            CategoryExample.Criteria criteria = categoryExample.createCriteria();
            criteria.andNameLike("%" + categoryQueryReq.getName() + "%");
        }
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
;
        //列表复制
        List<CategoryQueryResp> respList = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        return respList;
    }

    public PageResp<CategoryQueryResp> list(CategoryQueryReq categoryQueryReq) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        if(!ObjectUtils.isEmpty(categoryQueryReq.getName())){
            CategoryExample.Criteria criteria = categoryExample.createCriteria();
            criteria.andNameLike("%" + categoryQueryReq.getName() + "%");
        }
        PageHelper.startPage(categoryQueryReq.getPage(), categoryQueryReq.getSize());
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        LOG.info("总行数:{}",pageInfo.getTotal());
        LOG.info("总页数:{}",pageInfo.getPages());
//        List<CategoryResp> respList = new ArrayList<>();
//        for(Category category : categoryList){
//            CategoryResp categoryResp = new CategoryResp();
//            BeanUtils.copyProperties(category, categoryResp);
//
//            respList.add(categoryResp);
//        }
        //列表复制
        List<CategoryQueryResp> respList = CopyUtil.copyList(categoryList, CategoryQueryResp.class);
        PageResp pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }

    /**
     * @Author xiao
     * @Description 支持新增、更新电子书
     * @Date 16:50 2021-06-18
     * @Param [categorySaveReq]
     * @return void
     **/
    public void save(CategorySaveReq categorySaveReq) {
        Category category = CopyUtil.copy(categorySaveReq, Category.class);
        if(ObjectUtils.isEmpty(category.getId())){
            //新增
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }else {
            //更新
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    /*
     * @Author xiao
     * @Description //删除电子书
     * @Date 17:49 2021-06-18
     * @Param [id]
     * @return void
     **/
    public void delete(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

}
