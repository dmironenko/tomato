#!/bin/bash
set -e

mvnw clean install
mvnw docker:build