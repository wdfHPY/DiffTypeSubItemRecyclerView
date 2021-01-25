pwd
rm -rf cmd/pack/*.lonbon
rm -rf cmd/pack/*.7z
rm -rf cmd/pack/local
rm -rf cmd/pack/7za
mkdir cmd/pack/local
mkdir cmd/pack/7za
cp -avx cmd/pack/local-version/* cmd/pack/local/
7z a -t7z -r cmd/pack/local.7z cmd/pack/local-apk/*
gnome-terminal -t "lonbon_unpack_sub" -x bash -c "sh cmd/lonbon_pack_sub.sh;exec bash;"
