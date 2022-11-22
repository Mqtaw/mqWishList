#/usr/bin/env bash

echo 'Build...'

mvn clean package -Dmaven.test.skip

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/MqWishList-0.0.1-SNAPSHOT.jar \
    mqt@62.217.176.62:/home/mqt/


echo 'Restart server'

ssh -i ~/.ssh/id_rsa mqt@62.217.176.62 << EOF

pgrep java | xargs kill -9
java -jar MqWishList-0.0.1-SNAPSHOT.jar &

EOF

echo 'Bye'