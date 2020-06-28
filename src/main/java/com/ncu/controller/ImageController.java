package com.ncu.controller;

import jdk.nashorn.internal.ir.CallNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("imageClassification")
public class ImageController {
    List<String> list = new ArrayList<>();
    int imageIndex = 1;

    File trueFile = new File("e:/true");
    File falseFile = new File("e:/false");

    @GetMapping("aaa")
    public ModelAndView test(){
        File[] files = new File("e:/images").listFiles();
        ModelAndView modelAndView = new ModelAndView("index");
        for(File file: files){
            String fileName = file.getName();
            System.out.println(fileName);
            list.add(fileName);
        }
        modelAndView.addObject("fileName",list.get(0));
        return modelAndView;
    }
    @GetMapping("bbb")
    @ResponseBody
    public String testBBB(String flag,String imageName){
//        System.out.println("flag: " + flag + "imageName: " +imageName);
        File imageFile = new File("e:/images/"+imageName);

        try {
            if("true".equals(flag)){
                System.out.println("true");
                Files.move(imageFile.toPath(), new File(trueFile, imageName).toPath());
            }else{
                System.out.println("false");
                Files.move(imageFile.toPath(),new File(falseFile, imageName).toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.get(imageIndex++);
    }
}
