#!/bin/bash
set -e

sh -c 'cd api && ./create_image.sh'
sh -c 'cd ui && ./create_image.sh'