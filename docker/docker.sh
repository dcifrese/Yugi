#!/bin/sh -x

TEMP=$(mktemp -d)

ZIP=rocco.zip

cp ../target/universal/$ZIP $TEMP
cp Dockerfile $TEMP
cp docker-entrypoint.sh $TEMP

cd $TEMP
docker build -t playapp .

cd /
rm -rf $TEMP
