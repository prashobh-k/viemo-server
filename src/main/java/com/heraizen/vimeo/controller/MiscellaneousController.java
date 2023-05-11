package com.heraizen.vimeo.controller;

import com.clickntap.vimeo.Vimeo;
import com.clickntap.vimeo.VimeoResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*")
@Controller
public class MiscellaneousController {

    Vimeo vimeo= new Vimeo("f6560777169aa62ca581bf053f81e90b");

    @GetMapping("/verify")
    public String verify(@PathVariable String path) throws IOException {
        VimeoResponse res = vimeo.head(path);
        System.out.println(res);
        return res.getJson().toString();
    }

    @PostMapping("/search")
    public String search(@RequestParam("query-string") String queryString) throws IOException {
        VimeoResponse res = vimeo.searchVideos(queryString);
        System.out.println(res);
        return res.getJson().toString();
    }

}
