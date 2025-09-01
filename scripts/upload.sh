#!/bin/sh

aws s3 sync ./app/build/processedResources/wasmJs/main s3://$BUCKET_NAME --delete

aws s3 cp s3://$BUCKET_NAME s3://$BUCKET_NAME \
  --recursive \
  --exclude "*" \
  --include "*.wasm" \
  --metadata-directive REPLACE \
  --content-type "application/wasm" \
  --cache-control "max-age=31536000"

aws s3 cp s3://$BUCKET_NAME s3://$BUCKET_NAME \
  --recursive \
  --exclude "*" \
  --include "*.wasm.gz" \
  --metadata-directive REPLACE \
  --content-type "application/wasm" \
  --content-encoding "gzip" \
  --cache-control "max-age=31536000"

aws s3 cp s3://$BUCKET_NAME s3://$BUCKET_NAME \
  --recursive \
  --exclude "*" \
  --include "*.wasm.zst" \
  --metadata-directive REPLACE \
  --content-type "application/wasm" \
  --content-encoding "zstd" \
  --cache-control "max-age=31536000"

aws s3 cp s3://$BUCKET_NAME s3://$BUCKET_NAME \
  --recursive \
  --exclude "*" \
  --include "*.js" \
  --metadata-directive REPLACE \
  --content-type "application/javascript" \
  --cache-control "max-age=31536000"

aws s3 cp s3://$BUCKET_NAME s3://$BUCKET_NAME \
  --recursive \
  --exclude "*" \
  --include "*.js.gz" \
  --metadata-directive REPLACE \
  --content-type "application/javascript" \
  --content-encoding "gzip" \
  --cache-control "max-age=31536000"

aws s3 cp s3://$BUCKET_NAME s3://$BUCKET_NAME \
  --recursive \
  --exclude "*" \
  --include "*.js.zst" \
  --metadata-directive REPLACE \
  --content-type "application/javascript" \
  --content-encoding "zstd" \
  --cache-control "max-age=31536000"
