package com.example.wikidemo.controller;


import com.example.wikidemo.req.EbookReq;
import com.example.wikidemo.resp.CommonResp;
import com.example.wikidemo.resp.EbookResp;
import com.example.wikidemo.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiao
 * @Description 获取电子属数据
 * @Date 9:11 2021-06-17
 **/
@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq ebookReq) {
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp> list = ebookService.list(ebookReq);
        resp.setContent(list);
        return resp;
    }

}
