package com.example.wikidemo.controller;


import com.example.wikidemo.req.EbookQueryReq;
import com.example.wikidemo.req.EbookSaveReq;
import com.example.wikidemo.resp.CommonResp;
import com.example.wikidemo.resp.EbookQueryResp;
import com.example.wikidemo.resp.PageResp;
import com.example.wikidemo.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author xiao
 * @Description 获取电子书数据
 * @Date 9:11 2021-06-17
 **/
@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq ebookQueryReq) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(ebookQueryReq);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody EbookSaveReq ebookSaveReq) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(ebookSaveReq);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
}
