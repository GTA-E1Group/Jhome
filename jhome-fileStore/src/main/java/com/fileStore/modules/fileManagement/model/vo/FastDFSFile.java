package com.fileStore.modules.fileManagement.model.vo;

import lombok.Data;

@Data
public class FastDFSFile {

    final String FILE_DEFAULT_WIDTH = "120";
    final String FILE_DEFAULT_HEIGHT = "120";
    final String FILE_DEFAULT_AUTHOR = "system";

    private String appender_file_id;

    private long file_offset;

    private String fileName;

    private byte[] content;

    private String ext;

    private String height = FILE_DEFAULT_HEIGHT;

    private String width = FILE_DEFAULT_WIDTH;

    private String author = FILE_DEFAULT_AUTHOR;


    public FastDFSFile(String filefileName, byte[] content, String ext) {
        this.fileName = filefileName;
        this.content = content;
        this.ext = ext;
    }

    public FastDFSFile(String filefileName, byte[] content, String appender_file_id, long file_offset) {
        this.fileName = filefileName;
        this.content = content;
        this.appender_file_id = appender_file_id;
        this.file_offset = file_offset;
    }

}
