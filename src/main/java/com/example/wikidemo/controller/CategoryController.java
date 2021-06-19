package com.example.wikidemo.controller;


import com.example.wikidemo.req.CategoryQueryReq;
import com.example.wikidemo.req.CategorySaveReq;
import com.example.wikidemo.resp.CategoryQueryResp;
import com.example.wikidemo.resp.CommonResp;
import com.example.wikidemo.resp.PageResp;
import com.example.wikidemo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author xiao
 * @Description 获取分类数据
 * @Date 9:11 2021-06-17
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/all")
    public CommonResp all(CategoryQueryReq categoryQueryReq) {
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list = categoryService.all(categoryQueryReq);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/list")
    public CommonResp list(@Valid CategoryQueryReq categoryQueryReq) {
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryService.list(categoryQueryReq);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq categorySaveReq) {
        CommonResp resp = new CommonResp<>();
        categoryService.save(categorySaveReq);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }
}
