#!/bin/bash
set -e

npm install
ng build
docker build -t dmironenko/tomatoes-ui:1.0.0 .
