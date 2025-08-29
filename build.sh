#!/bin/sh

./gradlew wasmJsBrowserProductionWebpack

cp ./app/build/kotlin-webpack/wasmJs/productionExecutable/composeApp.js ./app/build/processedResources/wasmJs/main & \
  cp ./app/build/compileSync/wasmJs/main/productionExecutable/optimized/composeApp.wasm ./app/build/processedResources/wasmJs/main & \
  cp ./app/build/compose/skiko-runtime-processed-wasmjs/skiko.wasm ./app/build/processedResources/wasmJs/main

if [[ "$(uname)" == "Darwin" ]]; then
  alias sed=gsed
fi

cd ./app/build/processedResources/wasmJs/main || exit

sed -i '0,/p+"[a-z0-9]*\.wasm"/s//p+"composeApp.wasm"/' ./composeApp.js
sed -i '0,/p+"[a-z0-9]*\.wasm"/s//p+"skiko.wasm"/' ./composeApp.js

gzip -k --best ./composeApp.js
gzip -k --best ./composeApp.wasm
gzip -k --best ./skiko.wasm

zstd -k -15 ./composeApp.js
zstd -k -15 ./composeApp.wasm
zstd -k -15 ./skiko.wasm
