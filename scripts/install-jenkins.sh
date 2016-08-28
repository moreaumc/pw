#!/bin/bash
set -e

# cloned from https://github.com/rji/jenkins-standalone

# $JENKINS_VERSION should be an LTS release
JENKINS_VERSION="1.651.3"

# List of Jenkins plugins, in the format "${PLUGIN_NAME}/${PLUGIN_VERSION}"
JENKINS_PLUGINS=(
    "credentials/2.1.4"
    "email-ext/2.47"
    "git/2.5.3"
    "git-client/1.19.6"
    "greenballs/1.15"
    "job-dsl/1.50"
    "scm-api/1.0"
    "structs/1.3"
    "token-macro/1.12.1"
    "workflow-scm-step/1.14.2"
    "workflow-step-api/1.14.2"
)

JENKINS_WAR_MIRROR="http://mirrors.jenkins-ci.org/war-stable"
JENKINS_PLUGINS_MIRROR="http://mirrors.jenkins-ci.org/plugins"



# Ensure we have an accessible wget
if ! command -v wget > /dev/null; then
    echo "Error: wget not found in \$PATH"
    echo
    exit 1
fi


# Jenkins WAR file
if [[ ! -f "jenkins.war" ]]; then
    wget -nc "${JENKINS_WAR_MIRROR}/${JENKINS_VERSION}/jenkins.war"
fi

# Jenkins plugins
[[ ! -d "plugins" ]] && mkdir "plugins"
for plugin in ${JENKINS_PLUGINS[@]}; do
    IFS='/' read -a plugin_info <<< "${plugin}"
    plugin_path="${plugin_info[0]}/${plugin_info[1]}/${plugin_info[0]}.hpi"
    wget -nc -P plugins "${JENKINS_PLUGINS_MIRROR}/${plugin_path}"
done

# Jenkins config files
PORT=${PORT-"8080"}

mkdir -p logs

# Start the master
export JENKINS_HOME="$(pwd)"
java \
    -Dhudson.DNSMultiCast.disabled=true            \
    -Dhudson.udp=-1                                \
    -jar jenkins.war                               \
    -Djava.awt.headless=true                       \
    --webroot=war                                  \
    --httpPort=${PORT}                             \
    --ajp13Port=-1                                 \
    --httpListenAddress=0.0.0.0                    \
    --ajp13ListenAddress=127.0.0.1                 \
    --preferredClassLoader=java.net.URLClassLoader \
    --logfile=./logs/jenkins.log
