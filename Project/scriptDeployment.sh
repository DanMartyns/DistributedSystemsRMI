# You need to install sshpass to run the script correctly

bold=$(tput bold)
normal=$(tput sgr0)
echo "${bold}*** Script de Deployment ***${normal}"

export SSHPASS='enterro2019'


###

echo -e "\n${bold}* Copiar parâmetros de simulação *${normal}"
cp -f Constants/Constants.java Registry/src/MainPackage/Constants.java
cp -f Constants/Constants.java Manager/src/MainPackage/Constants.java
cp -f Constants/Constants.java Customer/src/MainPackage/Constants.java
cp -f Constants/Constants.java Mechanic/src/MainPackage/Constants.java
cp -f Constants/Constants.java Lounge/src/MainPackage/Constants.java
cp -f Constants/Constants.java RepairArea/src/MainPackage/Constants.java
cp -f Constants/Constants.java OutsideWorld/src/MainPackage/Constants.java
cp -f Constants/Constants.java Park/src/MainPackage/Constants.java
cp -f Constants/Constants.java SupplierSite/src/MainPackage/Constants.java
cp -f Constants/Constants.java GeneralInformationRepo/src/MainPackage/Constants.java
cp -f Constants/java.policy Registry/src/
cp -f Constants/java.policy Manager/src/
cp -f Constants/java.policy Customer/src/
cp -f Constants/java.policy Mechanic/src/
cp -f Constants/java.policy Lounge/src/
cp -f Constants/java.policy RepairArea/src/
cp -f Constants/java.policy OutsideWorld/src/
cp -f Constants/java.policy Park/src/
cp -f Constants/java.policy SupplierSite/src/
cp -f Constants/java.policy GeneralInformationRepo/src/

echo -e "\n${bold}* Copiar interfaces para o registry *${normal}"
cp Lounge/src/Interfaces/LoungeInterfaces.java Registry/src/Interfaces/
cp RepairArea/src/Interfaces/RepairAreaInterfaces.java Registry/src/Interfaces/
cp GeneralInformationRepo/src/Interfaces/GeneralInformationRepoInterfaces.java Registry/src/Interfaces/
cp OutsideWorld/src/Interfaces/OutsideWorldInterfaces.java Registry/src/Interfaces/
cp Park/src/Interfaces/ParkInterfaces.java Registry/src/Interfaces/
cp SupplierSite/src/Interfaces/SupplierSiteInterfaces.java Registry/src/Interfaces/

###

echo -e "\n${bold}* Comprimindo cada Entidade e Monitor *${normal}"
zip -r Registry.zip Registry
zip -r Manager.zip Manager
zip -r Customer.zip Customer
zip -r Mechanic.zip Mechanic
zip -r Lounge.zip Lounge
zip -r RepairArea.zip RepairArea
zip -r OutsideWorld.zip OutsideWorld
zip -r Park.zip Park
zip -r SupplierSite.zip SupplierSite
zip -r GeneralInformationRepo.zip GeneralInformationRepo

###

echo -e "\n${bold}* Cópia do código a executar em cada nó *${normal}"


echo -e "\n${bold}->${normal} A mover Registry e Logger para a máquina ${bold}1${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws01.ua.pt << !
    put -r Registry.zip
    put -r GeneralInformationRepo.zip
    bye
!

echo -e "\n${bold}->${normal} A mover Lounge para a máquina ${bold}2${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws02.ua.pt << !
    put -r Lounge.zip
    bye
!

echo -e "\n${bold}->${normal} A mover RepairArea para a máquina ${bold}3${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws03.ua.pt << !
    put -r RepairArea.zip
    bye
!

echo -e "\n${bold}->${normal} A mover OutsideWorld para a máquina ${bold}4${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws04.ua.pt << !
    put -r OutsideWorld.zip
    bye
!

echo -e "\n${bold}->${normal} A mover Park para a máquina ${bold}5${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws05.ua.pt << !
    put -r Park.zip
    bye
!

echo -e "\n${bold}->${normal} A mover SupplierSite para a máquina ${bold}6${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws06.ua.pt << !
    put -r SupplierSite.zip
    bye
!

echo -e "\n${bold}->${normal} A mover Manager para a máquina ${bold}7${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws07.ua.pt << !
    put -r Manager.zip
    bye
!

echo -e "\n${bold}->${normal} A mover Mechanic para a máquina ${bold}8${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws08.ua.pt << !
    put -r Mechanic.zip
    bye
!

echo -e "\n${bold}->${normal} A mover Customer para a máquina ${bold}9${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws09.ua.pt << !
    put -r Customer.zip
    bye
!


###

find . -name '*.zip' -delete

###

echo -e "\n${bold}* Compilação do código em cada nó *${normal}"


echo -e "\n${bold}->${normal} A compilar Registry e Logger na máquina ${bold}1${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws01.ua.pt << EOF
    lsof -n -i:22410 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
    lsof -n -i:22411 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
    lsof -n -i:22412 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    rm -rf Registry
    rm -rf GeneralInformationRepo

	unzip Registry.zip
	rm Registry.zip

    javac Registry/src/Interfaces/*.java Registry/src/MainPackage/*.java
    
    mkdir -p Registry/src/target/Interfaces/
    mkdir -p Registry/src/target/MainPackage/    

    mv Registry/src/Interfaces/*.class Registry/src/target/Interfaces/
    mv Registry/src/MainPackage/*.class Registry/src/target/MainPackage/
    mv Registry/src/java.policy Registry/src/target/    
    
    unzip GeneralInformationRepo.zip
	rm GeneralInformationRepo.zip
    
    javac GeneralInformationRepo/src/Interfaces/*.java GeneralInformationRepo/src/MainPackage/*.java 

    mkdir -p GeneralInformationRepo/src/target/Interfaces/
    mkdir -p GeneralInformationRepo/src/target/MainPackage/  

    mv GeneralInformationRepo/src/Interfaces/*.class GeneralInformationRepo/src/target/Interfaces/
    mv GeneralInformationRepo/src/MainPackage/*.class GeneralInformationRepo/src/target/MainPackage/
    mv GeneralInformationRepo/src/java.policy GeneralInformationRepo/src/target/

    cd Public

    rm -rf registry
    rm -rf logger

    mkdir -p registry
    mkdir -p logger
    
    cd registry
    mkdir -p classes
    cd ..
    
    cd logger
    mkdir -p classes
    cd ..

    cd ..
    mv Registry/src/target/* Public/registry/classes/
    mv GeneralInformationRepo/src/target/* Public/logger/classes/
    rm -rf Registry
    rm -rf GeneralInformationRepo

    exit
EOF

echo -e "\n${bold}->${normal} A compilar RepairArea na máquina ${bold}3${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws03.ua.pt << EOF
    lsof -n -i:22413 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9    
    
    rm -rf RepairArea

	unzip -o RepairArea.zip
	rm RepairArea.zip

    javac RepairArea/src/Interfaces/*.java RepairArea/src/MainPackage/*.java
    
    mkdir -p RepairArea/src/target/Interfaces/
    mkdir -p RepairArea/src/target/MainPackage/

    mv RepairArea/src/Interfaces/*.class RepairArea/src/target/Interfaces/
    mv RepairArea/src/MainPackage/*.class RepairArea/src/target/MainPackage/
    mv RepairArea/src/java.policy RepairArea/src/target/ 

    cd Public
    rm -rf repairArea
    mkdir -p repairArea

    cd repairArea
    mkdir -p classes
    cd ..

    cd ..
    mv RepairArea/src/target/* Public/repairArea/classes/
    rm -rf RepairArea

    exit
EOF

echo -e "\n${bold}->${normal} A compilar OutsideWorld na máquina ${bold}4${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws04.ua.pt << EOF
    lsof -n -i:22416 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    rm -rf OutsideWorld

	unzip -o OutsideWorld.zip
	rm OutsideWorld.zip

    javac OutsideWorld/src/Interfaces/*.java OutsideWorld/src/MainPackage/*.java

    mkdir -p OutsideWorld/src/target/Interfaces/
    mkdir -p OutsideWorld/src/target/MainPackage/    

    mv OutsideWorld/src/Interfaces/*.class OutsideWorld/src/target/Interfaces/
    mv OutsideWorld/src/MainPackage/*.class OutsideWorld/src/target/MainPackage/
    mv OutsideWorld/src/java.policy OutsideWorld/src/target/

    cd Public
    rm -rf outsideworld
    mkdir -p outsideworld

    cd outsideworld
    mkdir classes
    cd ..

    cd ..
    mv OutsideWorld/src/target/* Public/outsideworld/classes/
    rm -rf OutsideWorld

    exit
EOF

echo -e "\n${bold}->${normal} A compilar Park na máquina ${bold}5${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws05.ua.pt << EOF

    lsof -n -i:22414 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    rm -rf Park

    unzip -o Park.zip
	rm Park.zip

    javac Park/src/Interfaces/*.java Park/src/MainPackage/*.java

    mkdir -p Park/src/target/Interfaces/
    mkdir -p Park/src/target/MainPackage/

    mv Park/src/Interfaces/*.class Park/src/target/Interfaces/
    mv Park/src/MainPackage/*.class Park/src/target/MainPackage/
    mv Park/src/java.policy Park/src/target/     

    cd Public
    rm -rf park
    mkdir -p park

    cd park
    mkdir classes
    cd ..
    
    cd ..
    mv Park/src/target/* Public/park/classes/
    rm -rf Park

    exit
EOF

echo -e "\n${bold}->${normal} A compilar SupplierSite na máquina ${bold}6${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws06.ua.pt << EOF
    lsof -n -i:22415 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    rm -rf SupplierSite

    unzip -o SupplierSite.zip
	rm SupplierSite.zip

    javac SupplierSite/src/Interfaces/*.java SupplierSite/src/MainPackage/*.java

    mkdir -p SupplierSite/src/target/Interfaces/
    mkdir -p SupplierSite/src/target/MainPackage/

    mv SupplierSite/src/Interfaces/*.class SupplierSite/src/target/Interfaces/
    mv SupplierSite/src/MainPackage/*.class SupplierSite/src/target/MainPackage/
    mv SupplierSite/src/java.policy SupplierSite/src/target/       

    cd Public
    rm -rf suppliersite
    mkdir -p suppliersite

    cd suppliersite
    mkdir classes
    cd ..

    cd ..
    mv SupplierSite/src/target/* Public/suppliersite/classes/
    rm -rf SupplierSite

    exit
EOF

echo -e "\n${bold}->${normal} A compilar Lounge na máquina ${bold}2${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws02.ua.pt << EOF
    lsof -n -i:22417 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    rm -rf Lounge

	unzip -o Lounge.zip
	rm Lounge.zip

    javac Lounge/src/Interfaces/*.java Lounge/src/MainPackage/*.java
    
    mkdir -p Lounge/src/target/Interfaces/
    mkdir -p Lounge/src/target/MainPackage/

    mv Lounge/src/Interfaces/*.class Lounge/src/target/Interfaces/
    mv Lounge/src/MainPackage/*.class Lounge/src/target/MainPackage/
    mv Lounge/src/java.policy Lounge/src/target/ 

    cd Public
    rm -rf lounge
    mkdir -p lounge

    cd lounge
    mkdir -p classes
    cd ..

    cd ..
    mv Lounge/src/target/* Public/lounge/classes/
    rm -rf Lounge

    exit    
EOF


echo -e "\n${bold}->${normal} A compilar Manager na máquina ${bold}7${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws07.ua.pt << EOF
	rm -rf Manager
    
    unzip -o Manager.zip
	rm Manager.zip

    javac Manager/src/Interfaces/*.java Manager/src/MainPackage/*.java Manager/src/EntitiesState/*.java

    mkdir -p Manager/src/target/Interfaces/
    mkdir -p Manager/src/target/MainPackage/
    mkdir -p Manager/src/target/EntitiesState/

    mv Manager/src/Interfaces/*.class Manager/src/target/Interfaces/
    mv Manager/src/MainPackage/*.class Manager/src/target/MainPackage/
    mv Manager/src/EntitiesState/*.class Manager/src/target/EntitiesState/
    mv Manager/src/java.policy Manager/src/target/       

    cd Public
    rm -rf manager
    mkdir manager

    cd manager
    mkdir classes
    cd ..

    cd ..
    mv Manager/src/target/* Public/manager/classes/

    exit
EOF

echo -e "\n${bold}->${normal} A compilar Mechanic na máquina ${bold}8${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws08.ua.pt << EOF
    rm -rf Mechanic

    unzip -o Mechanic.zip
	rm Mechanic.zip

    javac Mechanic/src/Interfaces/*.java Mechanic/src/MainPackage/*.java Mechanic/src/EntitiesState/*.java

    mkdir -p Mechanic/src/target/Interfaces/
    mkdir -p Mechanic/src/target/MainPackage/
    mkdir -p Mechanic/src/target/EntitiesState/

    mv Mechanic/src/Interfaces/*.class Mechanic/src/target/Interfaces/
    mv Mechanic/src/MainPackage/*.class Mechanic/src/target/MainPackage/
    mv Mechanic/src/EntitiesState/*.class Mechanic/src/target/EntitiesState/
    mv Mechanic/src/java.policy Mechanic/src/target/          

    cd Public
    rm -rf mechanic
    mkdir -p mechanic
    
    cd mechanic
    mkdir classes
    cd ..

    cd ..
    mv Mechanic/src/target/* Public/mechanic/classes/
    rm -rf Mechanic

    exit
EOF

echo -e "\n${bold}->${normal} A compilar Customer na máquina ${bold}9${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws09.ua.pt << EOF
    rm -rf Customer

	unzip -o Customer.zip
	rm Customer.zip

    javac Customer/src/Interfaces/*.java Customer/src/MainPackage/*.java Customer/src/EntitiesState/*.java

    mkdir -p Customer/src/target/Interfaces/
    mkdir -p Customer/src/target/MainPackage/
    mkdir -p Customer/src/target/EntitiesState/

    mv Customer/src/Interfaces/*.class Customer/src/target/Interfaces/
    mv Customer/src/MainPackage/*.class Customer/src/target/MainPackage/
    mv Customer/src/EntitiesState/*.class Customer/src/target/EntitiesState/
    mv Customer/src/java.policy Customer/src/target/           

    cd Public
    rm -rf customer
    mkdir -p customer

    cd customer
    mkdir classes
    cd ..

    cd ..
    mv Customer/src/target/* Public/customer/classes/
    rm -rf Customer

    exit
EOF


###

echo -e "\n${bold}* Execução do código em cada nó *${normal}"


echo -e "\n${bold}->${normal} A iniciar e executar Registry e executar Logger na máquina ${bold}1${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws01.ua.pt << EOF

    cd Public/registry/classes
    rmiregistry -J-Djava.rmi.server.useCodebaseOnly=true 22410 > /dev/null 2>&1 &

    sleep 5
    
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.ServerRegisterRemoteObject > printRegister.txt &
    cd ../..

    
    sleep 5

    cd logger/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > printLogger.txt &

    exit
EOF

sleep 5

echo -e "\n${bold}->${normal} A executar Repair Area na máquina ${bold}3${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws03.ua.pt << EOF
    cd Public/repairArea/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > printRepairArea.txt &

    exit
EOF

sleep 5

echo -e "\n${bold}->${normal} A executar OutsideWorld na máquina ${bold}4${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws04.ua.pt << EOF
    cd Public/outsideworld/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > printoutside.txt &

    exit
EOF

sleep 5

echo -e "\n${bold}->${normal} A executar Park na máquina ${bold}5${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws05.ua.pt << EOF
    cd Public/park/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > printpark.txt &

    exit
EOF

sleep 5

echo -e "\n${bold}->${normal} A executar SupplierSite na máquina ${bold}6${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws06.ua.pt << EOF
    cd Public/suppliersite/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > printsupplier.txt &

    exit
EOF

sleep 5

echo -e "\n${bold}->${normal} A executar Lounge na máquina ${bold}2${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws02.ua.pt << EOF
    cd Public/lounge/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > printLounge.txt &

    exit
EOF

# Wait for the shared regions to be launched before lanching the intervening enities

sleep 10

echo -e "\n${bold}->${normal} A executar Manager na máquina ${bold}7${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws07.ua.pt << EOF
    cd Public/manager/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > printManager.txt &

    exit
EOF

sleep 5

echo -e "\n${bold}->${normal} A executar Mechanic na máquina ${bold}8${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws08.ua.pt << EOF
    cd Public/mechanic/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > printMechanic.txt &

    exit
EOF

sleep 5

echo -e "\n${bold}->${normal} A executar Customer na máquina ${bold}9${normal}"
sshpass -e ssh -tt -o StrictHostKeyChecking=no sd0401@l040101-ws09.ua.pt << EOF
    cd Public/customer/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram > printCustomer.txt &

    exit
EOF


