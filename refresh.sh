
echo "BUILDING A WAR"
mvn package 
echo "............."
sleep 3

echo "BUILDING A CONTAINER TOMCAT+WAR"
docker build . -t simplilearn-tomcat
echo "............."
sleep 3

echo "RUNNING THE CONTAINER"
docker run -p 8081:8080 -t simplilearn-tomcat
echo "............."
sleep 3