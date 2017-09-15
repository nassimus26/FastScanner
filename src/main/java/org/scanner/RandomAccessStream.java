package org.scanner;

import java.io.InputStream;

public interface RandomAccessStream {
    public InputStream newInputStream();
    public void setOffset(int offset);
}
