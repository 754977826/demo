package com.demo.document.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.document.bean.DocumentBean;
import com.demo.document.service.ServiceDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author nieyawei
 * @version 1.0
 * @className: ControllerWord
 * @description:
 * @date 2019-06-12 20:23
 */

@RestController
@RequestMapping(value = "/document")
public class ControllerWord {

    @Autowired
    private ServiceDocument serviceDocument;

    @PostMapping(value = "saveDocument")
    public JSONObject saveDocument(@RequestParam("document") DocumentBean document) {
        return serviceDocument.saveDocument(document);
    }

    @PostMapping(value = "saveDocumentFile")
    public JSONObject saveDocumentFile(@RequestParam("file") MultipartFile file) {

        /*String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();*/
        return serviceDocument.saveDocumentFile(file);

    }

    @PostMapping(value = "getDocumentList")
    public JSONObject getDocumentList() {

        /*String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();*/
        List<DocumentBean> list = serviceDocument.getDocList();
        JSONObject json = new JSONObject();
        json.put("Rows", list);
        json.put("Total", list.size());
        return json;

    }
}
