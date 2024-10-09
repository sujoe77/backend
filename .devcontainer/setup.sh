## update and install some things we should probably have
if [ -e /usr/local/sdkman/candidates/java ]
then
    echo "skip creating java home link!"
else
    mkdir /usr/local/sdkman/candidates/java
    ln -s /usr/lib/jvm/msopenjdk-current /usr/local/sdkman/candidates/java/current
fi