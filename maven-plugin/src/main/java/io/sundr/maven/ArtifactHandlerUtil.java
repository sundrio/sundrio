package io.sundr.maven;

import java.util.Objects;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;

public class ArtifactHandlerUtil {

  public static Artifact newArtifact(String groupId, String artifactId, String version, String scope, String type,
      String classifier) {

    DefaultArtifactHandler handler = new DefaultArtifactHandler(type);
    handler.setExtension(type);
    boolean addedToClasspath = !Objects.equals(scope, "provided");
    handler.setAddedToClasspath(addedToClasspath);

    return new DefaultArtifact(groupId, artifactId, version,
        scope, type, classifier,
        handler);
  }

}
