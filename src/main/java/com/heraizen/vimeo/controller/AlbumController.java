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
public class AlbumController {
    Vimeo vimeo= new Vimeo("f6560777169aa62ca581bf053f81e90b");

    @GetMapping("/albums")
    @ResponseBody
    public String getAlbums() throws IOException {
        VimeoResponse res = vimeo.get("/me/albums");
        System.out.println(res);
        return res.getJson().toString();

    }


    @PostMapping("/create-album")
    @ResponseBody
    public String createAlbums(@RequestParam String name) throws IOException {
        VimeoResponse res = vimeo.post("/me/albums",new JSONObject().append("name",name),null);
        System.out.println(res);
        return res.getJson().toString();

    }


    @PutMapping("/add-video-album")
    @ResponseBody
    public String addVideoToAlbum(@RequestParam("file") MultipartFile multipartFile,@RequestParam("path") String path ) throws IOException {
        VimeoResponse res = vimeo.put("/me/albums"+path,getFile(multipartFile),null);
        System.out.println(res);
        return res.getJson().toString();
    }


    private static File getFile(MultipartFile multipartfile) throws IOException {
        File file = new File(multipartfile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream( file );
        fos.write( multipartfile.getBytes() );
        fos.close();
        return file;
    }
}
