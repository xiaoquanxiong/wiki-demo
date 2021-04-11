package com.example.wikidemo.service;


import com.example.wikidemo.domain.Ebook;
import com.example.wikidemo.domain.EbookExample;
import com.example.wikidemo.mapper.EbookMapper;
import com.example.wikidemo.req.EbookReq;
import com.example.wikidemo.resp.EbookResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;


    public List<EbookResp> list(EbookReq ebookReq) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + ebookReq.getName() + "%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        List<EbookResp> respList = new ArrayList<>();
        for(Ebook ebook : ebookList){
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook, ebookResp);

            respList.add(ebookResp);
        }
        return respList;
    }

}
