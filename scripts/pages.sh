#!/bin/sh

if [[ "$(uname)" == "Darwin" ]]; then
  alias sed=gsed
fi

cd ./app/build/processedResources/wasmJs/main || exit

rm ./*.js* ./*.wasm*

URL=$(echo "$CLOUDFRONT_URL" | sed 's/\//\\\//g')

sed -i "s/composeApp.wasm/$URL\/composeApp.wasm/" ./index.html
sed -i "s/skiko.wasm/$URL\/skiko.wasm/" ./index.html
sed -i "s/composeApp.js/$URL\/composeApp.js/" ./index.html
