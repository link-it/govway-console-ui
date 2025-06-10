VERSION=$1
if [ -z "${VERSION}" ]
then
	echo "Versione non fornita"
	exit 1
fi

rm -rf govway-console-ui
mkdir govway-console-ui
cp package/script/pom.xml.template govway-console-ui/pom.xml
sed -i "s/VERSIONE-LIBRERIA/${VERSION}/g" govway-console-ui/pom.xml
mkdir govway-console-ui/src
cp -r struts-1.3.10/src/core/src/main/java/* govway-console-ui/src/
cp -r commons-chain-1.2/src/java/* govway-console-ui/src/
mkdir govway-console-ui/javadoc
echo "Riscrittura componenti interfaccia utente terza parte utilizzate da GovWayConsole per risoluzione problematiche di sicurezza e migrazione verso Jakarta EE" > govway-console-ui/javadoc/README
unzip -q package/target/govway-console-ui-${VERSION}.jar -d govway-console-ui/classes

