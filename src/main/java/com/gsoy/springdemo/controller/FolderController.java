package com.gsoy.springdemo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gsoy.springdemo.service.FtpService;
import io.swagger.v3.oas.annotations.Operation;
import nonapi.io.github.classgraph.json.JSONSerializer;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FolderController {

    List<String> folderList = new ArrayList<>();

    @Autowired
    FtpService ftpService;

    @GetMapping("/backupFolder")
    public String backupMove(@RequestParam(name = "year", required = false, defaultValue = "2023") String year) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient = ftpService.loginFtp("qnap.mangadenizi.com", 21, "gsoy", "G2610916s.");

        //ftpService.printTree("/GuraySOY/ServerBackup/sqlbackupandftp", ftpClient);
        //ftpService.createFolder(year, "/GuraySOY/ServerBackup/sqlbackupandftp", ftpClient);

        //ftpService.renameFile("/GuraySOY/ServerBackup/sqlbackupandftp/hotelmaywoodcom202304160800.zip","/GuraySOY/ServerBackup/sqlbackupandftp/20230416/hotelmaywoodcom202304160800.zip",ftpClient);


        File dir = new File("C:\\Users\\gsoy\\Qnap\\ServerBackup\\sqlbackupandftp");
        File[] list = dir.listFiles();

        for (File f : list) {
            if (f.isFile() && f.getName().contains(year)) {
                System.out.println(f.getPath() + " (File)");
                String name = f.getName().substring(f.getName().indexOf(year)).substring(0, 8);
                Path path = Paths.get(f.getParent() + "\\" + name + "\\");
                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                }
                Files.isWritable(f.toPath());
                if (Files.move(f.toPath(), Paths.get(path.toString() + "\\" + f.getName()), StandardCopyOption.REPLACE_EXISTING).isAbsolute()) {
                    folderList.add(f.toString());
                    Files.deleteIfExists(f.toPath());
                }
                ;
            }
        }

        return JSONSerializer.serializeObject(folderList);
    }

    @GetMapping("/folder")
    @Operation(summary = "Klasör silme işlemi")
    public String greeting(@RequestParam(name = "id", required = false, defaultValue = "1") String id) throws IOException {
        return Delete(id);
    }

    @GetMapping("/folderList")
    @Operation(summary = "Klasör Listesi")
    public String list() {
        File dir = new File("C:\\dev\\workspaceNeon\\CoreBanking\\CashManagement\\DEV");
        File[] list = dir.listFiles();

        for (File f : list) {
            if (f.isFile()) {
                System.out.println(f.getPath() + " (File)");
            } else if (f.isDirectory()) {
                System.out.println(f.getPath() + " (Directory)");
            }
        }
        return null;
    }

    private String Delete(String sWhatever) throws IOException {
        folderList.clear();
        String filepath;

        LocalDate today = LocalDate.now();             //Today
        LocalDate tomorrow = today.plusDays(1);     //Plus 1 day
        LocalDate yesterday = today.minusDays(1);   //Minus 1 day
        LocalDate month = today.plusMonths(1);

        //2. Add and substract 1 month from LocalDateTime

        LocalDateTime now = LocalDateTime.now();     //Current Date and Time
        LocalDateTime sameDayNextMonth = now.plusMonths(1);       //2018-08-14
        LocalDateTime sameDayLastMonth = now.minusMonths(1);

        switch (sWhatever) {
            case "1":

                filepath = "C:\\dev\\workspaceNeon\\CoreBanking\\CashManagement\\DEV";
                File file = new File(filepath);

                deleteDirectory(file);
                break;
            case "2":
                filepath = "C:\\dev\\workspaceNeon\\CoreBanking\\CashManagement\\TEST";
                file = new File(filepath);

                deleteDirectory(file);
                break;

            case "3":
                filepath = "C:\\dev\\workspaceNeon\\CoreBanking\\OpenBankingCore\\DEV";
                file = new File(filepath);

                deleteDirectory(file);

                file = new File("C:\\Users\\P34215\\Desktop\\Kurulumlar\\servers\\wildfly-10.1.0.Final-bus-CM\\standalone\\tmp");
                FileUtils.deleteDirectory(file);

                folderList.add(file + " Directory Deleted \n");

                break;
            case "4":
                filepath = "C:\\dev\\maven\\repository";
                file = new File(filepath);

                deleteDirectory(file);
                break;
            case "5":
                filepath = "C:\\dev\\workspaceNeon\\OpenBanking\\DEV";
                file = new File(filepath);

                deleteDirectory(file);
                break;
            case "6":
                filepath = "C:\\dev\\workspaceNeon\\OpenBanking\\TEST";
                file = new File(filepath);

                deleteDirectory(file);
                break;
            case "7":
                filepath = "C:\\dev\\workspaceNeon\\OpenBankingAccount\\DEV";
                file = new File(filepath);

                deleteDirectory(file);
                break;
            case "8":
                filepath = "C:\\dev\\workspaceNeon\\OpenBankingAccount\\TEST";
                file = new File(filepath);

                deleteDirectory(file);
                break;
        }
        folderList.add(sWhatever + "-- Finished");
        return JSONSerializer.serializeObject(folderList);
    }

    // function to delete subdirectories and files
    private void deleteDirectory(File file) throws IOException {
        // store all the paths of files and folders present
        // inside directory
        for (File subfile : file.listFiles()) {

            // if it is a subfolder,e.g Rohan and Ritik,
            // recursiley call function to empty subfolder
            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
                if (subfile.getName().contains("target")) {
                    // delete files and empty subfolders
                    FileUtils.deleteDirectory(subfile);
                    folderList.add(subfile + " Directory Deleted \n");
                }
            }
        }

    }


}