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

echo -e "\n${bold}* Copiar interfaces para o registry *${normal}"
cp Lounge/src/Interfaces/LoungeInterfaces.java Registry/src/Interfaces/
cp RepairArea/src/Interfaces/RepairAreaInterfaces.java Registry/src/Interfaces/
cp GeneralInformationRepo/src/Interfaces/GeneralInformationRepoInterfaces.java Registry/src/Interfaces/
cp OutsideWorld/src/Interfaces/OutsideWorldInterfaces.java Registry/src/Interfaces/
cp Park/src/Interfaces/ParkInterfaces.java Registry/src/Interfaces/
cp SupplierSite/src/Interfaces/SupplierSiteInterfaces.java Registry/src/Interfaces/


echo -e "\n${bold}* Cópia do código a executar em cada nó *${normal}"

echo -e "\n${bold}->${normal} A mover Registry para a máquina ${bold}1${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws01.ua.pt << EOF
    put -r Registry/
    bye
EOF

echo -e "\n${bold}->${normal} A mover General Information para a máquina ${bold}8${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws08.ua.pt << EOF
    put -r GeneralInformationRepo/
    bye
EOF

echo -e "\n${bold}->${normal} A mover Repair Area para a máquina ${bold}2${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws02.ua.pt << EOF
    put -r RepairArea/
    bye
EOF

echo -e "\n${bold}->${normal} A mover Park para a máquina ${bold}3${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws03.ua.pt << EOF
    put -r Park/
    bye
EOF

echo -e "\n${bold}->${normal} A mover Supplier Site para a máquina ${bold}4${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws04.ua.pt << EOF
    put -r SupplierSite/
    bye
EOF

echo -e "\n${bold}->${normal} A mover Outside World para a máquina ${bold}5${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws05.ua.pt << EOF
    put -r OutsideWorld/
    bye
EOF

echo -e "\n${bold}->${normal} A mover Lounge para a máquina ${bold}6${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws06.ua.pt << EOF
    put -r Lounge/
    bye
EOF

echo -e "\n${bold}->${normal} A mover Customer para a máquina ${bold}7${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws07.ua.pt << EOF
    put -r Customer/
    bye
EOF

echo -e "\n${bold}->${normal} A mover Mechanic para a máquina ${bold}9${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws09.ua.pt << EOF
    put -r Mechanic/
    bye
EOF

echo -e "\n${bold}->${normal} A mover Manager para a máquina ${bold}10${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws10.ua.pt << EOF
    put -r Manager/
    bye
EOF


echo -e "\n${bold}* Move completo *${normal}"

echo -e "\n${bold}* Compilação do código em cada nó *${normal}"


echo -e "\n${bold}->${normal} A compilar Registry na máquina ${bold}1${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws01.ua.pt << EOF
    lsof -n -i:22410 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
    lsof -n -i:22411 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9


    javac Registry/src/Interfaces/*.java Registry/src/MainPackage/*.java
    cp Registry/src/Interfaces/*.class Registry/src/dir_registry/Interfaces/
    cp Registry/src/MainPackage/*.class Registry/src/dir_registry/MainPackage/


    cd Public/
    mkdir dir_registry
    cd dir_registry/
    mkdir classes
    cd ../..
    cp -r Registry/src/dir_registry/* Public/dir_registry/classes/

    exit
EOF

echo -e "\n${bold}->${normal} A compilar Logger na máquina ${bold}8${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws08.ua.pt << EOF
    lsof -n -i:22412 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    javac GeneralInformationRepo/src/Interfaces/*.java GeneralInformationRepo/src/MainPackage/*.java
    cp GeneralInformationRepo/src/Interfaces/*.class GeneralInformationRepo/src/dir_logger/Interfaces/
    cp GeneralInformationRepo/src/MainPackage/*.class GeneralInformationRepo/src/dir_logger/MainPackage/

    cd Public/
    mkdir dir_logger
    cd dir_logger/
    mkdir classes
    cd ../..
    cp -r GeneralInformationRepo/src/dir_logger/* Public/dir_logger/classes/

    exit
EOF

echo -e "\n${bold}->${normal} A compilar Repair Area na máquina ${bold}2${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws02.ua.pt << EOF
    lsof -n -i:22413 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    javac RepairArea/src/Interfaces/*.java RepairArea/src/MainPackage/*.java
    cp RepairArea/src/Interfaces/*.class RepairArea/src/dir_repairArea/Interfaces/
    cp RepairArea/src/MainPackage/*.class RepairArea/src/dir_repairArea/MainPackage/

    cd Public/
    mkdir classes
    cd ..
    cp -r RepairArea/src/dir_repairArea/* Public/classes/

    exit
EOF

echo -e "\n${bold}->${normal} A compilar Park na máquina ${bold}3${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws03.ua.pt << EOF
    lsof -n -i:22414 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    javac Park/src/Interfaces/*.java Park/src/MainPackage/*.java
    cp Park/src/Interfaces/*.class Park/src/dir_park/Interfaces/
    cp Park/src/MainPackage/*.class Park/src/dir_park/MainPackage/

    cd Public/
    mkdir classes
    cd ..
    cp -r Park/src/dir_park/* Public/classes/

    exit
EOF

echo -e "\n${bold}->${normal} A compilar Supplier Site na máquina ${bold}4${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws04.ua.pt << EOF
  lsof -n -i:22415 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9


  javac SupplierSite/src/Interfaces/*.java SupplierSite/src/MainPackage/*.java
  cp SupplierSite/src/Interfaces/*.class SupplierSite/src/dir_supplierSite/Interfaces/
  cp SupplierSite/src/MainPackage/*.class SupplierSite/src/dir_supplierSite/MainPackage/

  cd Public/
  mkdir classes
  cd ..
  cp -r SupplierSite/src/dir_supplierSite/* Public/classes/

  exit
EOF

echo -e "\n${bold}->${normal} A compilar Outside World na máquina ${bold}5${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws05.ua.pt << EOF
    lsof -n -i:22416 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9


    javac OutsideWorld/src/Interfaces/*.java OutsideWorld/src/MainPackage/*.java
    cp OutsideWorld/src/Interfaces/*.class OutsideWorld/src/dir_outsideWorld/Interfaces/
    cp OutsideWorld/src/MainPackage/*.class OutsideWorld/src/dir_outsideWorld/MainPackage/

    cd Public/
    mkdir classes
    cd ..
    cp -r OutsideWorld/src/dir_outsideWorld/* Public/classes/

    exit
EOF

echo -e "\n${bold}->${normal} A compilar Lounge na máquina ${bold}6${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws06.ua.pt << EOF
    lsof -n -i:22417 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9


    javac Lounge/src/Interfaces/*.java Lounge/src/MainPackage/*.java
    cp Lounge/src/Interfaces/*.class Lounge/src/dir_lounge/Interfaces/
    cp Lounge/src/MainPackage/*.class Lounge/src/dir_lounge/MainPackage/

    cd Public/
    mkdir classes
    cd ..
    cp -r Lounge/src/dir_lounge/* Public/classes/

    exit
EOF


echo -e "\n${bold}->${normal} A compilar Customer na máquina ${bold}7${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws07.ua.pt << EOF

   javac Customer/src/Interfaces/*.java Customer/src/MainPackage/*.java Customer/src/EntitiesState/*.java
   cp Customer/src/Interfaces/*.class Customer/src/dir_customers/Interfaces/
   cp Customer/src/MainPackage/*.class Customer/src/dir_customers/MainPackage/
   cp Customer/src/EntitiesState/*.class Customer/src/dir_customers/EntitiesState/

   cd Public/
   mkdir classes
   cd ..
   cp -r Customer/src/dir_customers/* Public/classes/

    exit
EOF

echo -e "\n${bold}->${normal} A compilar Mechanic na máquina ${bold}10${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws10.ua.pt << EOF

    javac Mechanic/src/Interfaces/*.java Mechanic/src/MainPackage/*.java Mechanic/src/EntitiesState/*.java
    cp Mechanic/src/Interfaces/*.class Mechanic/src/dir_mechanic/Interfaces/
    cp Mechanic/src/MainPackage/*.class Mechanic/src/dir_mechanic/MainPackage/
    cp Mechanic/src/EntitiesState/*.class Mechanic/src/dir_mechanic/EntitiesState/

    cd Public/
    mkdir classes
    cd ..
    cp -r Mechanic/src/dir_mechanic/* Public/classes/

    exit
EOF

echo -e "\n${bold}->${normal} A compilar Manager na máquina ${bold}9${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws09.ua.pt << EOF

    javac Manager/src/Interfaces/*.java Manager/src/MainPackage/*.java Manager/src/EntitiesState/*.java
    cp Manager/src/Interfaces/*.class Manager/src/dir_manager/Interfaces/
    cp Manager/src/MainPackage/*.class Manager/src/dir_manager/MainPackage/
    cp Manager/src/EntitiesState/*.class Manager/src/dir_manager/EntitiesState/

    cd Public/
    mkdir classes
    cd ..
    cp -r Manager/src/dir_manager/* Public/classes/

    exit
EOF
echo -e "\n${bold}* Compilação completa *${normal}"


echo -e "\n${bold}* Execução do código em cada nó *${normal}"


echo -e "\n${bold}->${normal} A iniciar e executar Registry na máquina ${bold}1${normal}"
sshpass -e ssh -t -t  -o StrictHostKeyChecking=no sd0401@l040101-ws01.ua.pt << EOF
  lsof -n -i:22410 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
  lsof -n -i:22411 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9
  lsof -n -i:22412 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

  cd Public/classes/dir_registry
  rmiregistry -J-Djava.rmi.server.useCodebaseOnly=true 22410 & 
  sleep 5

  nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/Public/dir_registry/classes/"\
  -Djava.rmi.server.useCodebaseOnly=true\
  -Djava.security.policy=java.policy\
  MainPackage.ServerRegisterRemoteObject &
  exit
EOF

echo -e "\n${bold}->${normal} A iniciar executar Logger na máquina ${bold}8${normal}"
sshpass -e ssh -t -t  -o StrictHostKeyChecking=no sd0401@l040101-ws08.ua.pt << EOF
  lsof -n -i:22412 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

  cd Public/classes/
  nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/dir_registry/classes/"\
  -Djava.rmi.server.useCodebaseOnly=true\
  -Djava.security.policy=java.policy\
  MainPackage.MainProgram &
  exit
EOF

sleep 5

echo -e "\n${bold}->${normal} A executar Repair Area na máquina ${bold}2${normal}"
sshpass -e ssh -t -t  -o StrictHostKeyChecking=no sd0401@l040101-ws02.ua.pt << EOF
    lsof -n -i:22413 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/dir_registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram &
    exit
EOF

sleep 1

echo -e "\n${bold}->${normal} A executar Park na máquina ${bold}3${normal}"
sshpass -e ssh -t -t  -o StrictHostKeyChecking=no sd0401@l040101-ws03.ua.pt << EOF
    lsof -n -i:22414 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/dir_registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram &
    exit
EOF

sleep 1

echo -e "\n${bold}->${normal} A executar Supplier Site na máquina ${bold}4${normal}"
sshpass -e ssh -t -t  -o StrictHostKeyChecking=no sd0401@l040101-ws04.ua.pt << EOF
lsof -n -i:22415 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/dir_registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram &
    exit
EOF

sleep 1

echo -e "\n${bold}->${normal} A executar Outside World na máquina ${bold}5${normal}"
sshpass -e ssh -t -t  -o StrictHostKeyChecking=no sd0401@l040101-ws05.ua.pt << EOF
lsof -n -i:22416 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/dir_registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram &

    exit
EOF

sleep 1

echo -e "\n${bold}->${normal} A executar Lounge na máquina ${bold}6${normal}"
sshpass -e ssh -t -t  -o StrictHostKeyChecking=no sd0401@l040101-ws06.ua.pt << EOF
lsof -n -i:22417 | grep LISTEN | awk '{ print $2 }' | uniq | xargs -r kill -9

    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/dir_registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram &

    exit
EOF

# Wait for the shared regions to be launched before lanching the intervening enities

sleep 5

echo -e "\n${bold}->${normal} A executar Customer na máquina ${bold}7${normal}"
sshpass -e ssh -t -t  -o StrictHostKeyChecking=no sd0401@l040101-ws07.ua.pt << EOF
    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/dir_registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram &

    exit
EOF

sleep 1

echo -e "\n${bold}->${normal} A executar Mechanic na máquina ${bold}10${normal}"
sshpass -e ssh -t -t  -o StrictHostKeyChecking=no sd0401@l040101-ws10.ua.pt << EOF
    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/dir_registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram &

    exit
EOF

sleep 1

echo -e "\n${bold}->${normal} A executar Manager na máquina ${bold}9${normal}"
sshpass -e ssh -t -t -o StrictHostKeyChecking=no sd0401@l040101-ws09.ua.pt << EOF
    cd Public/classes/
    nohup java -Djava.rmi.server.codebase="http://l040101-ws01.ua.pt/sd0401/dir_registry/classes/"\
    -Djava.rmi.server.useCodebaseOnly=true\
    -Djava.security.policy=java.policy\
    MainPackage.MainProgram  &
    exit
EOF

echo -e "\n${bold}* Execução do código completo *${normal}"
echo -e "\n${bold}* Deploy remoto completo *${normal}"
