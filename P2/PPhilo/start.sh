#!/bin/bash

# start.sh

set -e

HOME_DIR="$HOME"/Escritorio
ts=$(date +%Y%m%d%H%M%S)
OUT=${HOME_DIR}/laboCSD_${ts}.webm
#LOCK=${HOME_DIR}/recording.lock
#STDIN=${HOME_DIR}/ffmpeg.stdin
LOG=${HOME_DIR}/ffmpeg.log
ERR=${HOME_DIR}/ffmpeg.err

# Lanzar ffmpeg

echo "Iniciando grabación de "${OUT}
echo "Pulsa q para finalizar (...tendrás que esperar medio minuto)"

ffmpeg -y \
  -framerate 2 \
  -f x11grab -i "$DISPLAY" \
  -c:v libvpx-vp9 -b:v 500k -crf 33 -an \
  ${OUT} > ${LOG} 2>&1
