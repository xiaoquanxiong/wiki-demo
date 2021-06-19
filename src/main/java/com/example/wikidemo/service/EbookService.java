package com.example.wikidemo.service;


import com.example.wikidemo.domain.Ebook;
import com.example.wikidemo.domain.EbookExample;
import com.example.wikidemo.mapper.EbookMapper;
import com.example.wikidemo.req.EbookQueryReq;
import com.example.wikidemo.req.EbookSaveReq;
import com.example.wikidemo.resp.EbookQueryResp;
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
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<EbookQueryResp> list(EbookQueryReq ebookQueryReq) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(ebookQueryReq.getName())){
            criteria.andNameLike("%" + ebookQueryReq.getName() + "%");
        }
        PageHelper.startPage(ebookQueryReq.getPage(), ebookQueryReq.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数:{}",pageInfo.getTotal());
        LOG.info("总页数:{}",pageInfo.getPages());
//        List<EbookResp> respList = new ArrayList<>();
//        for(Ebook ebook : ebookList){
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook, ebookResp);
//
//            respList.add(ebookResp);
//        }
        //列表复制
        List<EbookQueryResp> respList = CopyUtil.copyList(ebookList, EbookQueryResp.class);
        PageResp pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }

    /**
     * @Author xiao
     * @Description 支持新增、更新电子书
     * @Date 16:50 2021-06-18
     * @Param [ebookSaveReq]
     * @return void
     **/
    public void save(EbookSaveReq ebookSaveReq) {
        Ebook ebook = CopyUtil.copy(ebookSaveReq, Ebook.class);
        if(ObjectUtils.isEmpty(ebook.getId())){
            //新增
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }else {
            //更新
            ebookMapper.updateByPrimaryKey(ebook);
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
        ebookMapper.deleteByPrimaryKey(id);
    }
}
