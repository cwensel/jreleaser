/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020-2021 Andres Almiray.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jreleaser.model.validation;

import org.jreleaser.model.JReleaserContext;
import org.jreleaser.model.Project;

import java.util.List;

import static org.jreleaser.model.Project.DEFAULT_SNAPSHOT_PATTERN;
import static org.jreleaser.model.Project.PROJECT_VERSION;
import static org.jreleaser.model.Project.SNAPSHOT_PATTERN;
import static org.jreleaser.util.StringUtils.isBlank;

/**
 * @author Andres Almiray
 * @since 0.1.0
 */
public abstract class ProjectValidator extends Validator {
    public static void validateProject(JReleaserContext context, List<String> errors) {
        Project project = context.getModel().getProject();

        if (isBlank(project.getName())) {
            errors.add("project.name must not be blank");
        }

        project.setVersion(
            checkProperty(context.getModel().getEnvironment(),
                PROJECT_VERSION,
                "project.version",
                project.getVersion(),
                errors));

        project.setSnapshotPattern(
            checkProperty(context.getModel().getEnvironment(),
                SNAPSHOT_PATTERN,
                "project.snapshotPattern",
                project.getSnapshotPattern(),
                DEFAULT_SNAPSHOT_PATTERN));

        if (isBlank(project.getDescription())) {
            errors.add("project.description must not be blank");
        }
        if (isBlank(project.getWebsite())) {
            errors.add("project.website must not be blank");
        }
        if (isBlank(project.getLicense())) {
            errors.add("project.license must not be blank");
        }
        if (isBlank(project.getLongDescription())) {
            project.setLongDescription(project.getDescription());
        }
        if (project.getAuthors().isEmpty()) {
            errors.add("project.authors must not be empty");
        }

        validateJava(context, project, errors);
    }

    private static void validateJava(JReleaserContext context, Project project, List<String> errors) {
        if (!project.getJava().isSet()) return;

        project.getJava().setEnabled(true);

        if (isBlank(project.getJava().getArtifactId())) {
            project.getJava().setArtifactId(project.getName());
        }
        if (isBlank(project.getJava().getGroupId())) {
            errors.add("project.java.groupId must not be blank");
        }
        if (isBlank(project.getJava().getArtifactId())) {
            errors.add("project.java.artifactId must not be blank");
        }
        if (!project.getJava().isMultiProjectSet()) {
            project.getJava().setMultiProject(false);
        }
    }
}