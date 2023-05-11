package com.heraizen.vimeo.controller;

import com.clickntap.vimeo.Vimeo;
import com.clickntap.vimeo.VimeoResponse;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CrudEndpoint {

    Vimeo vimeo= new Vimeo("f6560777169aa62ca581bf053f81e90b");

    @GetMapping("/videos")
    @ResponseBody
    public String getAllVideos() throws IOException {
        VimeoResponse res = vimeo.getVideos();
        System.out.println(res);

        return res.getJson().toString();
    }

    @PostMapping(path="/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestParam("file") MultipartFile multipartfile) throws IOException {
        if (!multipartfile.isEmpty()) {
            File file = getFile(multipartfile);
            String link = getUploadLink(file);
            VimeoResponse res = vimeo.uploadVideo(link, file);
            return "File uploaded successfully.";
        } else {
            return "Failed to upload file.";
        }
    }

    private String getUploadLink(File file) throws IOException {
        if(file.length()!=0){
        Map<String, String> privacy= new HashMap<>();
        privacy.put("embed","public");
        VimeoResponse uploadLink = vimeo.beginUploadVideo(file.length(), file.getName(), privacy);
       return (String) ((JSONObject)(uploadLink.getJson().get("upload"))).get("upload_link");}
        else{
            throw new FileNotFoundException();
        }

    }

    private static File getFile(MultipartFile multipartfile) throws IOException {
        File file = new File(multipartfile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream( file );
        fos.write( multipartfile.getBytes() );
        fos.close();
        return file;
    }




}
