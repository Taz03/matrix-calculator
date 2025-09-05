#!/bin/sh

if [[ "$(uname)" == "Darwin" ]]; then
  alias sed=gsed
fi

cd ./app/build/processedResources/wasmJs/main || exit

rm ./*.js* ./*.wasm*

sed -i "s/composeApp.wasm/$CLOUDFRONT_URL\/composeApp.wasm/" ./index.html
sed -i "s/skiko.wasm/$CLOUDFRONT_URL\/skiko.wasm/" ./index.html
