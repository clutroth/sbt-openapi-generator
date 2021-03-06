/*
 * Copyright (c) 2020 OpenAPI-Generator Contributors (https://openapi-generator.tech)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
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

package org.openapitools.generator.sbt.plugin

import org.openapitools.codegen.CodegenConstants
import sbt.{File, settingKey, taskKey}

trait OpenApiGeneratorKeys {
  final val openApiGenerate = taskKey[Seq[File]]("Generate code via Open API Tools Generator for Open API 2.0 or 3.x specification documents.")
  final val openApiGenerators = taskKey[Unit]("Print list of available generators")

  final val openApiInputSpec = settingKey[String]("The Open API 2.0/3.x specification location.")
  final val openApiOutputDir = settingKey[String]("The output target directory into which code will be generated.")
  final val openApiConfigFile = settingKey[String]("Path to json configuration file.\n" +
    "File content should be in a json format { \"optionKey\":\"optionValue\", \"optionKey1\":\"optionValue1\"...}\n" +
    "Supported options can be different for each language. Run config-help -g {generator name} command for language specific config options.")
  final val openApiAdditionalProperties = settingKey[Map[String, String]]("Sets additional properties that can be referenced by the mustache templates in the format of name=value,name=value.\n" +
    "You can also have multiple occurrences of this option.")
  @deprecated("use openApiGlobalProperties instead") final val openApiSystemProperties = settingKey[Map[String, String]]("Sets specified system properties.")
  final val openApiGlobalProperties = settingKey[Map[String, String]]("Sets specified system properties.")

  final val openApiVerbose = settingKey[Option[Boolean]]("The verbosity of generation")
  final val openApiValidateSpec = settingKey[Option[Boolean]]("Whether or not an input specification should be validated upon generation.")
  final val openApiGeneratorName = settingKey[String]("The name of the generator which will handle codegen. (see \"openApiGenerators\" task)")
  final val openApiTemplateDir = settingKey[String]("The template directory holding a custom template.")
  final val openApiAuth = settingKey[String]("Adds authorization headers when fetching the OpenAPI definitions remotely.\n" +
    "Pass in a URL-encoded string of name:header with a comma separating multiple values")
  final val openApiSkipOverwrite = settingKey[Option[Boolean]]("Specifies if the existing files should be overwritten during the generation.")
  final val openApiPackageName = settingKey[String](CodegenConstants.PACKAGE_NAME_DESC)
  final val openApiApiPackage = settingKey[String](CodegenConstants.API_PACKAGE_DESC)
  final val openApiModelPackage = settingKey[String](CodegenConstants.MODEL_PACKAGE_DESC)
  final val openApiModelNamePrefix = settingKey[String](CodegenConstants.MODEL_NAME_PREFIX_DESC)
  final val openApiModelNameSuffix = settingKey[String](CodegenConstants.MODEL_NAME_SUFFIX_DESC)
  final val openApiInstantiationTypes = settingKey[Map[String, String]]("Sets instantiation type mappings.")
  final val openApiTypeMappings = settingKey[Map[String, String]]("Sets mappings between OpenAPI spec types and generated code types.")
  final val openApiServerVariables = settingKey[Map[String, String]]("Sets server variable for server URL template substitution, in the format of name=value,name=value.\n"
    + "You can also have multiple occurrences of this option.")

  final val openApiLanguageSpecificPrimitives = settingKey[List[String]]("Specifies additional language specific primitive types in the format of type1,type2,type3,type3. For example: String,boolean,Boolean,Double.")
  final val openApiImportMappings = settingKey[Map[String, String]]("Specifies mappings between a given class and the import that should be used for that class.")
  final val openApiInvokerPackage = settingKey[String](CodegenConstants.INVOKER_PACKAGE_DESC)
  //TODO: change to sbt organization
  final val openApiGroupId = settingKey[String](CodegenConstants.GROUP_ID_DESC)
  //TODO: change to sbt name
  final val openApiId = settingKey[String](CodegenConstants.ARTIFACT_ID_DESC)

  final val openApiLibrary = settingKey[String](CodegenConstants.LIBRARY_DESC)
  final val openApiGitHost = settingKey[String](CodegenConstants.GIT_HOST_DESC)
  final val openApiGitUserId = settingKey[String](CodegenConstants.GIT_USER_ID_DESC)
  final val openApiGitRepoId = settingKey[String](CodegenConstants.GIT_REPO_ID_DESC)
  final val openApiReleaseNote = settingKey[String](CodegenConstants.RELEASE_NOTE_DESC)
  final val openApiHttpUserAgent = settingKey[String](CodegenConstants.HTTP_USER_AGENT_DESC)
  final val openApiReservedWordsMappings = settingKey[Map[String, String]]("Specifies how a reserved name should be escaped to.")
  final val openApiIgnoreFileOverride = settingKey[String](CodegenConstants.IGNORE_FILE_OVERRIDE_DESC)
  final val openApiRemoveOperationIdPrefix = settingKey[Option[Boolean]]("Remove prefix of operationId, e.g. config_getId => getId")

  /**
   * Defines which API-related files should be generated. This allows you to create a subset of generated files (or none at all).
   *
   * This option enables/disables generation of ALL api-related files.
   *
   * NOTE: Configuring any one of [apiFilesConstrainedTo], [modelFilesConstrainedTo], or [supportingFilesConstrainedTo] results
   * in others being disabled. That is, OpenAPI Generator considers any one of these to define a subset of generation.
   * For more control over generation of individual files, configure an ignore file and refer to it via [ignoreFileOverride].
   */
  final val openApiApiFilesConstrainedTo = settingKey[List[String]]("Defines which API-related files should be generated. This allows you to create a subset of generated files (or none at all).")

  /**
   * Defines which model-related files should be generated. This allows you to create a subset of generated files (or none at all).
   *
   * NOTE: Configuring any one of [apiFilesConstrainedTo], [modelFilesConstrainedTo], or [supportingFilesConstrainedTo] results
   * in others being disabled. That is, OpenAPI Generator considers any one of these to define a subset of generation.
   * For more control over generation of individual files, configure an ignore file and refer to it via [ignoreFileOverride].
   */
  final val openApiModelFilesConstrainedTo = settingKey[List[String]]("Defines which model-related files should be generated. This allows you to create a subset of generated files (or none at all).")

  /**
   * Defines which supporting files should be generated. This allows you to create a subset of generated files (or none at all).
   *
   * Supporting files are those related to projects/frameworks which may be modified
   * by consumers.
   *
   * NOTE: Configuring any one of [apiFilesConstrainedTo], [modelFilesConstrainedTo], or [supportingFilesConstrainedTo] results
   * in others being disabled. That is, OpenAPI Generator considers any one of these to define a subset of generation.
   * For more control over generation of individual files, configure an ignore file and refer to it via [ignoreFileOverride].
   */
  final val openApiSupportingFilesConstrainedTo = settingKey[List[String]]("Defines which supporting files should be generated. This allows you to create a subset of generated files (or none at all).")

  /**
   * Defines whether or not model-related _test_ files should be generated.
   *
   * This option enables/disables generation of ALL model-related _test_ files.
   *
   * For more control over generation of individual files, configure an ignore file and
   * refer to it via [ignoreFileOverride].
   */
  final val openApiGenerateModelTests = settingKey[Option[Boolean]](CodegenConstants.GENERATE_MODEL_TESTS_DESC)

  /**
   * Defines whether or not model-related _documentation_ files should be generated.
   *
   * This option enables/disables generation of ALL model-related _documentation_ files.
   *
   * For more control over generation of individual files, configure an ignore file and
   * refer to it via [ignoreFileOverride].
   */
  final val openApiGenerateModelDocumentation = settingKey[Option[Boolean]]("Defines whether or not model-related _documentation_ files should be generated.")

  /**
   * Defines whether or not api-related _test_ files should be generated.
   *
   * This option enables/disables generation of ALL api-related _test_ files.
   *
   * For more control over generation of individual files, configure an ignore file and
   * refer to it via [ignoreFileOverride].
   */
  final val openApiGenerateApiTests = settingKey[Option[Boolean]](CodegenConstants.GENERATE_API_TESTS_DESC)

  /**
   * Defines whether or not api-related _documentation_ files should be generated.
   *
   * This option enables/disables generation of ALL api-related _documentation_ files.
   *
   * For more control over generation of individual files, configure an ignore file and
   * refer to it via [ignoreFileOverride].
   */
  final val openApiGenerateApiDocumentation = settingKey[Option[Boolean]]("Defines whether or not api-related _documentation_ files should be generated.")
  final val openApiWithXml = settingKey[Option[Boolean]]("A special-case setting which configures some generators with XML support. In some cases, this forces json OR xml, so the default here is false.")

  final val openApiLogToStderr = settingKey[Option[Boolean]]("To write all log messages (not just errors) to STDOUT")

  /**
   * To enable the file post-processing hook. This enables executing an external post-processor (usually a linter program).
   * This only enables the post-processor. To define the post-processing command, define an environment variable such as
   * LANG_POST_PROCESS_FILE (e.g. GO_POST_PROCESS_FILE, SCALA_POST_PROCESS_FILE). Please open an issue if your target
   * generator does not support this functionality.
   */
  final val openApiEnablePostProcessFile = settingKey[Option[Boolean]](CodegenConstants.ENABLE_POST_PROCESS_FILE_DESC)

  final val openApiSkipValidateSpec = settingKey[Option[Boolean]]("To skip spec validation. When true, we will skip the default behavior of validating a spec before generation.")

  /**
   * To generate alias (array, list, map) as model. When false, top-level objects defined as array, list, or map will result in those
   * definitions generated as top-level Array-of-items, List-of-items, Map-of-items definitions.
   * When true, A model representation either containing or extending the array,list,map (depending on specific generator implementation) will be generated.
   */
  final val openApiGenerateAliasAsModel = settingKey[Option[Boolean]](CodegenConstants.GENERATE_ALIAS_AS_MODEL_DESC)

}
