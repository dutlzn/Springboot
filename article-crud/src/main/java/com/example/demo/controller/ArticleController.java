package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
//import com.example.demo.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 文章 控制器
 */
@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    /**
     * 文章列表 采用分页(重点掌握)
     */
    @GetMapping("")
    public ModelAndView articleLists(
            @RequestParam(value = "start", defaultValue = "0") Integer start,
            @RequestParam(value = "limit", defaultValue = "5") Integer limit
    ){
        start = start<0?0:start; // 小于0恢复到0
        Sort sort = Sort.by(Sort.Direction.DESC, "id");//倒序 aes 正序
        Pageable pageable = PageRequest.of(start, limit, sort);
        Page<Article> page = articleRepository.findAll(pageable);
        ModelAndView mav = new ModelAndView(("article/list"));
        mav.addObject("page", page);
        return mav;
    }
    /**
     * 根据id获取文章
     */
    @GetMapping("/{id}")
    public ModelAndView getArticle(@PathVariable("id") Integer id) throws Exception{
        Article article = articleRepository.findById(id);
        ModelAndView mav = new ModelAndView("article/show");
        mav.addObject("article", article);
        return mav;
    }

    /**
     * 增加文章
     */
    @GetMapping("/add")
    public String addArticle() throws Exception {
        return "article/add";
    }

    /**
     * 保存文章
     */
    @PostMapping("")
    public String saveArticle(Article model) throws Exception {
//        model.setCreateTime(new Date());
        articleRepository.save(model);
//        return "redirect:"; 为什么错
        return "redirect:/article";//重新跳转到列表页
    }

    /**
     * 删除文章
     */
    @DeleteMapping("/{id}")
    public String del(@PathVariable("id") long id) throws Exception{
        articleRepository.deleteById(id);
        return "redirect:/article";
    }


    /**
     * 编辑
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editArticle(@PathVariable("id") long id) throws Exception{
        Article model = articleRepository.findById(id);
        ModelAndView mav = new ModelAndView("article/edit");
        mav.addObject("article", model);
        return mav;
    }

    /**
     * 修改
     */
    @PutMapping("/{id}")
    public String editArticleSave(Article model, long id) throws Exception{
        model.setId(id);
//        model.setLastmodifiedBy(new Date());
        articleRepository.save(model);
        return "redirect:";
    }
}
