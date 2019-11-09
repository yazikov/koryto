#!/bin/bash

DIR=~/data
URL=http://192.168.0.59:8080

cd $DIR

FILE="$(ls -t | head -n 1)"

curl -F "file=@$FILE" $URL/loadData
