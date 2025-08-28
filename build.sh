#!/bin/sh

./gradlew wasmJsBrowserProductionWebpack

cp ./app/build/kotlin-webpack/wasmJs/productionExecutable/composeApp.js ./app/build/processedResources/wasmJs/main & \
  cp ./app/build/compileSync/wasmJs/main/productionExecutable/optimized/composeApp.wasm ./app/build/processedResources/wasmJs/main & \
  cp ./app/build/compose/skiko-runtime-processed-wasmjs/skiko.wasm ./app/build/processedResources/wasmJs/main

if [[ "$(uname)" == "Darwin" ]]; then
  alias sed=gsed
fi

sed -i '0,/p+"[a-z0-9]*\.wasm"/s//p+"composeApp.wasm"/' ./app/build/processedResources/wasmJs/main/composeApp.js
sed -i '0,/p+"[a-z0-9]*\.wasm"/s//p+"skiko.wasm"/' ./app/build/processedResources/wasmJs/main/composeApp.js
