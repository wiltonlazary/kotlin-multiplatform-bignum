./gradlew build \
bignum:publishJvmPublicationToSnapshotRepository \
bignum:publishJsPublicationToSnapshotRepository \
bignum:publishKotlinMultiplatformPublicationToSnapshotRepository \
bignum:publishLinuxPublicationToSnapshotRepository \
bignum:publishLinuxArm64PublicationToSnapshotRepository \
bignum:publishAndroidNativeX64ToSnapshotRepository \
bignum:publishAndroidNativeX86ToSnapshotRepository \
bignum:publishAndroidNativeArm32ToSnapshotRepository \
bignum:publishAndroidNativeArm64ToSnapshotRepository \
bignum:publishWasmJsPublicationToSnapshotRepository \
bignum:publishWasmWasiPublicationToSnapshotRepository || exit 1

./gradlew \
bignum-serialization-kotlinx:publishJsPublicationToSnapshotRepository \
bignum-serialization-kotlinx:publishJvmPublicationToSnapshotRepository \
bignum-serialization-kotlinx:publishJsPublicationToSnapshotRepository \
bignum-serialization-kotlinx:publishKotlinMultiplatformPublicationToSnapshotRepository \
bignum-serialization-kotlinx:publishLinuxPublicationToSnapshotRepository \
bignum-serialization-kotlinx:publishLinuxArm64PublicationToSnapshotRepository \
bignum-serialization-kotlinx:publishAndroidNativeX64ToSnapshotRepository \
bignum-serialization-kotlinx:publishAndroidNativeX86ToSnapshotRepository \
bignum-serialization-kotlinx:publishAndroidNativeArm32ToSnapshotRepository \
bignum-serialization-kotlinx:publishAndroidNativeArm64ToSnapshotRepository \
bignum-serialization-kotlinx:publishWasmJsPublicationToSnapshotRepository \
bignum-serialization-kotlinx:publishWasmWasiPublicationToSnapshotRepository || exit 1
exit 0
