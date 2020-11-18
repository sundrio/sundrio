/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.swagger.language;

import java.util.Optional;

public class ArtifactUtil {

    public static final String DELIMETER = ":";
    public static final int GROUP_ID_INDEX = 0;
    public static final int ARTIFACT_ID_INDEX = 1;
    public static final int VERSION_INDEX = 2;
    public static final int CLASSIFIER_INDEX = 3;

    /**
     * Extracts the groupId from the specified coordinates
     * @param the maven coordinates
     * @return the groupId
     */
    public static String groupId(String coordinates)  {
        return split(coordinates)[GROUP_ID_INDEX];
    }

    /**
     * Extracts the artifactId from the specified coordinates
     * @param the maven coordinates
     * @return the artifactId
     */
    public static String artifactId(String coordinates)  {
        return split(coordinates)[ARTIFACT_ID_INDEX];
    }

    /**
     * Extracts the version from the specified coordinates
     * @param the maven coordinates
     * @return the version
     */
    public static String version(String coordinates)  {
        return split(coordinates)[VERSION_INDEX];
    }

    /**
     * Extracts the classifier from the specified coordinates
     * @param the maven coordinates
     * @return the classifier as an {@link Optional} string.
     */
    public static Optional<String> classifier(String coordinates)  {
        String[] parts = split(coordinates);
        if (parts.length == 4) {
            return Optional.of(parts[CLASSIFIER_INDEX]);
        }
        return Optional.empty();
    }

    /**
     * Splits the specified maven coordinates to individual parts: groupId, artifactId, version and optionally classifier.
     * The coordiante format is <groupId>:<artifactId>:<version>(:<classifier>)
     * @param coordinates the specified maven coordinates as a string.
     * @reutrn a {@link String} array with all the coordinate parts
     */
    private static String[] split(String coordinates) {
        if (coordinates == null) {
            throw new NullPointerException("Expected maven coordinates!");
        }
        String[] parts = coordinates.split(DELIMETER);
        if (parts.length < 3 || parts.length > 4) {
            throw new IllegalArgumentException("Maven coordinates are expected to follow the format <groupId>:<artifactId>:<version>(:<classifier>)!");
        }
        return parts;
    }
}
