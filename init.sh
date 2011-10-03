git config --global core.autocrlf true
echo "# Encoding configured"
export MAVEN_OPTS="-Xms200m -Xmx512m -XX:MaxPermSize=512m"
echo "# JVM configured : " $MAVEN_OPTS