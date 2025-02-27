/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020-2023 The JReleaser authors.
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
package org.jreleaser.gradle.plugin.dsl.checksum

import groovy.transform.CompileStatic
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.jreleaser.util.Algorithm

/**
 *
 * @author Andres Almiray
 * @since 0.4.0
 */
@CompileStatic
interface Checksum {
    Property<String> getName()

    Property<Boolean> getIndividual()

    Property<Boolean> getArtifacts()

    Property<Boolean> getFiles()

    ListProperty<Algorithm> getAlgorithms()

    void algorithm(String algorithm)
}