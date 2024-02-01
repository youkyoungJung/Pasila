package org.ssafy.pasila.global.common.file;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;

public class FilenameAwareInputStreamResource extends InputStreamResource {
    private final String filename;
    private final long contentLength;

    public FilenameAwareInputStreamResource(InputStream inputStream, long contentLength, String filename) {
        super(inputStream);
        this.filename = filename;
        this.contentLength = contentLength;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public long contentLength() throws IOException {
        return contentLength;
    }
}
