package com.heraizen.vimeo.controller;

import com.clickntap.vimeo.Vimeo;
import com.clickntap.vimeo.VimeoResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@CrossOrigin(origins = "*")
@Controller
public class FolderController {
    Vimeo vimeo= new Vimeo("f6560777169aa62ca581bf053f81e90b");

    @GetMapping("/folders")
    @ResponseBody
    public String getFolders() throws IOException {
        VimeoResponse res = vimeo.get("/me/projects");
        System.out.println(res);
        return res.getJson().toString();

    }
    @GetMapping("/specific-folders")
    @ResponseBody
    public String getVideosFromSpecificFolders(@PathVariable String path) throws IOException {
        VimeoResponse res = vimeo.get(path);
        System.out.println(res);
        return res.getJson().toString();

    }

    @PostMapping("/create-folder")
    @ResponseBody
    public String createFolder(@RequestParam("userendpoint") String userendpoint,@RequestParam("name") String name) throws IOException {
        VimeoResponse res = vimeo.post(userendpoint,new JSONObject().append("name",name),null);
        System.out.println(res);
        return res.getJson().toString();

    }


    @PutMapping("/add-video-folder")
    @ResponseBody
    public String addVideoToFolder(@RequestParam("path") String path ) throws IOException {
        VimeoResponse res = vimeo.put(path);
        System.out.println(res);
        return res.getJson().toString();
    }



}
