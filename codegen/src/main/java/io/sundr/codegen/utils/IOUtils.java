/*
 * Copyright 2016 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.codegen.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.tools.FileObject;

public class IOUtils {

    /**
     * Closes multiple {@link Closeable} objects swallowing exceptions.
     * @param cloasebales   The {@link Closeable} objects.
     */
    public static void closeQuietly(Closeable... cloasebales) {
        if (cloasebales != null) {
            for (Closeable c : cloasebales) {
                try {
                    if (c != null) {
                        c.close();
                    }
                } catch (IOException ex) {
                    //ignore
                }
            }
        }
    }


    /**
     * Copy one {@link FileObject} into an other.
     * @param source    The source {@link FileObject}.
     * @param target    The target {@link FileObject}.
     * @throws IOException
     */
    public static void copy(FileObject source, FileObject target) throws IOException {
        InputStream in = source.openInputStream();
        OutputStream out = target.openOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = in.read(buffer);
            while (len != -1) {
                out.write(buffer, 0, len);
                len = in.read(buffer);
            }
        } finally {
            in.close();
            out.close();
        }
    }
}
