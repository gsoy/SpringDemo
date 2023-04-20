package com.gsoy.springdemo.service;

import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FtpService {

    public FTPClient loginFtp(String host, int port, String username, String password) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.addProtocolCommandListener(new ProtocolCommandListener() {
            @Override
            public void protocolCommandSent(ProtocolCommandEvent protocolCommandEvent) {
                System.out.printf("[%s][%d] Command sent : [%s]-%s", Thread.currentThread().getName(),
                        System.currentTimeMillis(), protocolCommandEvent.getCommand(),
                        protocolCommandEvent.getMessage());
            }

            @Override
            public void protocolReplyReceived(ProtocolCommandEvent protocolCommandEvent) {
                System.out.printf("[%s][%d] Reply received : %s", Thread.currentThread().getName(),
                        System.currentTimeMillis(), protocolCommandEvent.getMessage());
            }
        });
        ftpClient.connect(host, port);
        ftpClient.login(username, password);
        return ftpClient;
    }

    public void printTree(String path, FTPClient ftpClient) throws Exception {
        for (FTPFile ftpFile : ftpClient.listFiles(path)) {
            System.out.println();
            System.out.printf("[printTree][%d]\n", System.currentTimeMillis());
            System.out.printf("[printTree][%d] Get name : %s \n", System.currentTimeMillis(), ftpFile.getName());
            System.out.printf("[printTree][%d] Get timestamp : %s \n", System.currentTimeMillis(), ftpFile.getTimestamp().getTimeInMillis());
            System.out.printf("[printTree][%d] Get group : %s \n", System.currentTimeMillis(), ftpFile.getGroup());
            System.out.printf("[printTree][%d] Get link : %s \n", System.currentTimeMillis(), ftpFile.getLink());
            System.out.printf("[printTree][%d] Get user : %s \n", System.currentTimeMillis(), ftpFile.getUser());
            System.out.printf("[printTree][%d] Get type : %s \n", System.currentTimeMillis(), ftpFile.getType());
            System.out.printf("[printTree][%d] Is file : %s \n", System.currentTimeMillis(), ftpFile.isFile());
            System.out.printf("[printTree][%d] Is directory : %s \n", System.currentTimeMillis(), ftpFile.isDirectory());
            System.out.printf("[printTree][%d] Formatted string : %s \n", System.currentTimeMillis(), ftpFile.toFormattedString());
            System.out.println();

            if (ftpFile.isDirectory()) {
                printTree(path + File.separator + ftpFile.getName(), ftpClient);
            }
        }
    }


    public void createFolder(String year, String path, FTPClient ftpClient) throws IOException {
        for (FTPFile f : ftpClient.listFiles(path)) {
            if (f.isFile() && f.getName().contains(year)) {
                String name = f.getName().substring(f.getName().indexOf(year)).substring(0, 8);

                String folder = path + "/" + name ;
                if (ftpClient.list(folder) > 1 ? false: true) {
                    ftpClient.makeDirectory(folder);
                }
            }
        }
    }

    public void renameFile(String oldPath, String newPath, FTPClient ftpClient) throws Exception {
        System.out.println();
        System.out.printf("[renameFile][%d] Is success to rename file : from %s to %s -> %b",
                System.currentTimeMillis(), oldPath, newPath, ftpClient.rename(oldPath, newPath));
        System.out.println();
    }
}