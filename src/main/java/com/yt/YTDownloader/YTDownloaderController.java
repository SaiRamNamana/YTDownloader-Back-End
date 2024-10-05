package com.yt.YTDownloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/download")
@CrossOrigin
public class YTDownloaderController {
    @Autowired
    private YouTubeDownloaderService youTubeDownloaderService;

    @GetMapping
    public String downloadVideo(@RequestParam String videoUrl) {
        return youTubeDownloaderService.downloadVideo(videoUrl);
    }
}
