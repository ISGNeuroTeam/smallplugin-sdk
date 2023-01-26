# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.3.1]
### Fixed
- no time to explain

## [0.3.0]
### Changed
- The testing tool's DataFrame matchers checks data frames schemas equality first.
- README's includes description of how to link the **SMaLL Plugin SDK** library to extension project
 and how to override configuration accessed via `PluginUtils` fixture.
- Dispatcher_sdk dependency updated to 1.2.0.

## [0.2.0] - 2020-10-23
### Added
- The testing tool for developers of the **SMaLL Plugin Core** extensions.
### Changed
- The `dispatcher-sdk` library dependency now is compiled into this module. 

## [0.1.0] - 2020-10-16
### Added
- Ability to create a custom `fit` command extension via the `FitModel` trait.
- Ability to create a custom `score` command extension via the `ScoreModel` trait.
- Ability to create a custom `apply` command extension via the `ApplyModel` trait.
- This CHANGELOG
- Makefile
- License
