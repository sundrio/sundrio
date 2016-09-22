/*
 * Copyright 2015 The original authors.
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
package io.sundr.maven;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.sundr.maven.filter.Filters;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.ArtifactHandler;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Exclusion;
import org.apache.maven.plugin.logging.Log;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactDescriptorRequest;
import org.eclipse.aether.resolution.ArtifactDescriptorResult;
import org.eclipse.aether.resolution.ArtifactRequest;

/**
 * Resolves external BOMs and provides its managed dependencies.
 * Using Aether to get full information about the managed dependencies.
 */
public class ExternalBomResolver {

    private MavenSession mavenSession;

    private ArtifactHandler artifactHandler;

    private RepositorySystem system;

    private RepositorySystemSession session;

    private List<RemoteRepository> remoteRepositories;

    private Log logger;

    public ExternalBomResolver(MavenSession mavenSession, ArtifactHandler artifactHandler, RepositorySystem system, RepositorySystemSession session, List<RemoteRepository> remoteRepositories, Log
            logger) {
        this.mavenSession = mavenSession;
        this.artifactHandler = artifactHandler;
        this.system = system;
        this.session = session;
        this.remoteRepositories = remoteRepositories;
        this.logger = logger;
    }

    public Map<Artifact, Dependency> resolve(BomConfig config) throws Exception {
        Map<Artifact, Dependency> dependencies = new LinkedHashMap<Artifact, Dependency>();
        if (config != null && config.getImports() != null) {
            for (BomImport bom : config.getImports()) {
                Map<Artifact, Dependency> deps = resolve(bom);
                for (Map.Entry<Artifact, Dependency> e : deps.entrySet()) {
                    if (!dependencies.containsKey(e.getKey())) {
                        // order is important for imported boms
                        dependencies.put(e.getKey(), e.getValue());
                    }
                }
            }
        }
        return dependencies;
    }

    private Map<Artifact, Dependency> resolve(BomImport bom) throws Exception {
        logger.info("Resolving " + bom + " to get managed dependencies ");

        Map<Artifact, Dependency> dependencies = resolveDependencies(bom);
        Map<Artifact, Dependency> filteredDependencies = Filters.filter(dependencies, Filters.createDependencyManagementFilter(mavenSession, bom));

        int included = filteredDependencies.size();
        int total = dependencies.size();
        logger.info("Included " + included + "/" + total + " dependencies from BOM " + bom);
        return filteredDependencies;
    }

    private Map<Artifact, Dependency> resolveDependencies(BomImport bom) throws Exception {
        org.eclipse.aether.artifact.Artifact artifact = new org.eclipse.aether.artifact.DefaultArtifact(bom.getGroupId(), bom.getArtifactId(), "pom", bom.getVersion());

        ArtifactRequest artifactRequest = new ArtifactRequest(artifact, remoteRepositories, null);
        system.resolveArtifact(session, artifactRequest); // To get an error when the artifact does not exist

        ArtifactDescriptorRequest req = new ArtifactDescriptorRequest(artifact, remoteRepositories, null);
        ArtifactDescriptorResult res = system.readArtifactDescriptor(session, req);

        Map<Artifact, Dependency> mavenDependencies = new LinkedHashMap<Artifact, Dependency>();
        if (res.getManagedDependencies() != null) {
            for (org.eclipse.aether.graph.Dependency dep : res.getManagedDependencies()) {
                mavenDependencies.put(toMavenArtifact(dep), toMavenDependency(dep));
            }
        }

        return mavenDependencies;
    }

    private Dependency toMavenDependency(org.eclipse.aether.graph.Dependency from) {
        org.eclipse.aether.artifact.Artifact fromArt = from.getArtifact();

        Dependency dependency = new Dependency();
        dependency.setGroupId(fromArt.getGroupId());
        dependency.setArtifactId(fromArt.getArtifactId());
        dependency.setVersion(fromArt.getVersion());
        dependency.setType(fromArt.getExtension());
        dependency.setScope(Artifact.SCOPE_COMPILE.equals(from.getScope()) ? null : from.getScope());
        dependency.setClassifier(fromArt.getClassifier());
        dependency.setOptional(dependency.isOptional());

        if (from.getExclusions() != null && from.getExclusions().size() > 0) {
            List<Exclusion> exclusions = new LinkedList<Exclusion>();
            for (org.eclipse.aether.graph.Exclusion fromEx : from.getExclusions()) {
                Exclusion ex = new Exclusion();
                ex.setGroupId(fromEx.getGroupId());
                ex.setArtifactId(fromEx.getArtifactId());
                exclusions.add(ex);
            }
            dependency.setExclusions(exclusions);
        }

        return dependency;
    }


    private Artifact toMavenArtifact(org.eclipse.aether.graph.Dependency dependency) {
        org.eclipse.aether.artifact.Artifact sa = dependency.getArtifact();
        Artifact a = new DefaultArtifact(sa.getGroupId(), sa.getArtifactId(), sa.getVersion(), dependency.getScope(), sa.getExtension(), sa.getClassifier(), artifactHandler);
        return a;
    }

}
