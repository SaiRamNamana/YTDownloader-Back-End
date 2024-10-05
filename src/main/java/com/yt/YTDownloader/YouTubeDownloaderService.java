package com.yt.YTDownloader;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class YouTubeDownloaderService {

    private static final String DOWNLOAD_DIR = "/home/rguktogole/Downloads/YTDownloads/";

    public String downloadVideo(String videoUrl) {
        // Create directory if it does not exist
        File downloadDir = new File(DOWNLOAD_DIR);
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }

        try {
            // Use yt-dlp to download the video
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "yt-dlp",
                    "-f", "best[height<=480]", // Specify format for normal quality (480p or lower)
                    "--ignore-errors",
                    "-o", DOWNLOAD_DIR + "%(title)s.%(ext)s",
                    videoUrl
            );

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return "Video downloaded successfully!\n";
            } else {
                return "Error downloading video.\n";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error downloading video: " + e.getMessage();
        }
    }
}
