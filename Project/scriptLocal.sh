bold=$(tput bold)
normal=$(tput sgr0)
echo "${bold}*** Script Local ***${normal}"

###

echo -e "\n${bold}* Copiar parâmetros de simulação *${normal}"
cp Constants/Constants_local.java Registry/src/MainPackage/Constants.java
cp Constants/Constants_local.java Manager/src/MainPackage/Constants.java
cp Constants/Constants_local.java Customer/src/MainPackage/Constants.java
cp Constants/Constants_local.java Mechanic/src/MainPackage/Constants.java
cp Constants/Constants_local.java Lounge/src/MainPackage/Constants.java
cp Constants/Constants_local.java RepairArea/src/MainPackage/Constants.java
cp Constants/Constants_local.java OutsideWorld/src/MainPackage/Constants.java
cp Constants/Constants_local.java Park/src/MainPackage/Constants.java
cp Constants/Constants_local.java SupplierSite/src/MainPackage/Constants.java
cp Constants/Constants_local.java GeneralInformationRepo/src/MainPackage/Constants.java



echo -e "\n${bold}* Compilação do código em cada nó *${normal}"


echo -e "\n${bold}->${normal} A compilar Registry"
javac Registry/src/Interfaces/*.java Registry/src/MainPackage/*.java
cp Registry/src/Interfaces/*.class Registry/src/dir_registry/Interfaces/
cp Registry/src/MainPackage/*.class Registry/src/dir_registry/MainPackage/


echo -e "\n${bold}->${normal} A compilar Manager"
javac Manager/src/Interfaces/*.java Manager/src/MainPackage/*.java Manager/src/EntitiesState/*.java
cp Manager/src/Interfaces/*.class Manager/src/dir_manager/Interfaces/
cp Manager/src/MainPackage/*.class Manager/src/dir_manager/MainPackage/
cp Manager/src/EntitiesState/*.class Manager/src/dir_manager/EntitiesState/

echo -e "\n${bold}->${normal} A compilar Customer"
javac Customer/src/Interfaces/*.java Customer/src/MainPackage/*.java Customer/src/EntitiesState/*.java
cp Customer/src/Interfaces/*.class Customer/src/dir_customers/Interfaces/
cp Customer/src/MainPackage/*.class Customer/src/dir_customers/MainPackage/
cp Customer/src/EntitiesState/*.class Customer/src/dir_customers/EntitiesState/

echo -e "\n${bold}->${normal} A compilar Mechanics"
javac Mechanic/src/Interfaces/*.java Mechanic/src/MainPackage/*.java Mechanic/src/EntitiesState/*.java
cp Mechanic/src/Interfaces/*.class Mechanic/src/dir_mechanic/Interfaces/
cp Mechanic/src/MainPackage/*.class Mechanic/src/dir_mechanic/MainPackage/
cp Mechanic/src/EntitiesState/*.class Mechanic/src/dir_mechanic/EntitiesState/


echo -e "\n${bold}->${normal} A compilar Lounge"
javac Lounge/src/Interfaces/*.java Lounge/src/MainPackage/*.java
cp Lounge/src/Interfaces/*.class Lounge/src/dir_lounge/Interfaces/
cp Lounge/src/MainPackage/*.class Lounge/src/dir_lounge/MainPackage/



echo -e "\n${bold}->${normal} A compilar Repair Area"
javac RepairArea/src/Interfaces/*.java RepairArea/src/MainPackage/*.java
cp RepairArea/src/Interfaces/*.class RepairArea/src/dir_repairArea/Interfaces/
cp RepairArea/src/MainPackage/*.class RepairArea/src/dir_repairArea/MainPackage/


echo -e "\n${bold}->${normal} A compilar Outside World"
javac OutsideWorld/src/Interfaces/*.java OutsideWorld/src/MainPackage/*.java
cp OutsideWorld/src/Interfaces/*.class OutsideWorld/src/dir_outsideWorld/Interfaces/
cp OutsideWorld/src/MainPackage/*.class OutsideWorld/src/dir_outsideWorld/MainPackage/

echo -e "\n${bold}->${normal} A compilar Park"
javac Park/src/Interfaces/*.java Park/src/MainPackage/*.java
cp Park/src/Interfaces/*.class Park/src/dir_park/Interfaces/
cp Park/src/MainPackage/*.class Park/src/dir_park/MainPackage/

echo -e "\n${bold}->${normal} A compilar Supplier Site"
javac SupplierSite/src/Interfaces/*.java SupplierSite/src/MainPackage/*.java
cp SupplierSite/src/Interfaces/*.class SupplierSite/src/dir_supplierSite/Interfaces/
cp SupplierSite/src/MainPackage/*.class SupplierSite/src/dir_supplierSite/MainPackage/


echo -e "\n${bold}->${normal} A compilar Logger"
javac GeneralInformationRepo/src/Interfaces/*.java GeneralInformationRepo/src/MainPackage/*.java
cp GeneralInformationRepo/src/Interfaces/*.class GeneralInformationRepo/src/dir_logger/Interfaces/
cp GeneralInformationRepo/src/MainPackage/*.class GeneralInformationRepo/src/dir_logger/MainPackage/

echo -e "\n${bold}* Execução do código em cada nó *${normal}"

echo -e "\n${bold}* A iniciar Registry *${normal}"
cd Registry/
rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 8081 &
regId=$!
cd ..

echo -e "\n${bold}->${normal} A executar Registry"
cd Registry/src/dir_registry/
java -Djava.rmi.server.codebase="file:///Users/giselapinto/Desktop/4°ano/SD/Pratica/Trabalho3/DistributedSystemsRMI/Project/Registry/src/dir_registry/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     MainPackage.ServerRegisterRemoteObject &
cd ../../..
sleep 3
echo -e "\n${bold}->${normal} A executar Logger"
cd GeneralInformationRepo/src/dir_logger/
java -Djava.rmi.server.codebase="file:///Users/giselapinto/Desktop/4°ano/SD/Pratica/Trabalho3/DistributedSystemsRMI/Project/GeneralInformationRepo/src/dir_logger/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     MainPackage.MainProgram &
cd ../../..
sleep 3
echo -e "\n${bold}->${normal} A executar Repair Area"
cd RepairArea/src/dir_repairArea/
java -Djava.rmi.server.codebase="file:///Users/giselapinto/Desktop/4°ano/SD/Pratica/Trabalho3/DistributedSystemsRMI/Project/RepairArea/src/dir_repairArea/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     MainPackage.MainProgram &
cd ../../..
sleep 3
echo -e "\n${bold}->${normal} A executar Park"
cd Park/src/dir_park/
java -Djava.rmi.server.codebase="file:///Users/giselapinto/Desktop/4°ano/SD/Pratica/Trabalho3/DistributedSystemsRMI/Project/Park/src/dir_park/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     MainPackage.MainProgram &
cd ../../..
sleep 3
echo -e "\n${bold}->${normal} A executar Supplier Site"
cd SupplierSite/src/dir_supplierSite/
java -Djava.rmi.server.codebase="file:///Users/giselapinto/Desktop/4°ano/SD/Pratica/Trabalho3/DistributedSystemsRMI/Project/SupplierSite/src/dir_supplierSite/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     MainPackage.MainProgram &
cd ../../..
sleep 3
echo -e "\n${bold}->${normal} A executar Outside World"
cd OutsideWorld/src/dir_outsideWorld/
java -Djava.rmi.server.codebase="file:///Users/giselapinto/Desktop/4°ano/SD/Pratica/Trabalho3/DistributedSystemsRMI/Project/OutsideWorld/src/dir_outsideWorld/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     MainPackage.MainProgram &
cd ../../..
sleep 3
echo -e "\n${bold}->${normal} A executar Lounge"
cd Lounge/src/dir_lounge/
java -Djava.rmi.server.codebase="file:///Users/giselapinto/Desktop/4°ano/SD/Pratica/Trabalho3/DistributedSystemsRMI/Project/Lounge/src/dir_lounge/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     MainPackage.MainProgram &
cd ../../..
sleep 3
echo -e "\n${bold}->${normal} A executar Customer"
cd Customer/src/dir_customers/
java -Djava.rmi.server.codebase="file:///Users/giselapinto/Desktop/4°ano/SD/Pratica/Trabalho3/DistributedSystemsRMI/Project/Customer/src/dir_customers/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     MainPackage.MainProgram &
cd ../../..
sleep 3
echo -e "\n${bold}->${normal} A executar Mechanic"
cd Mechanic/src/dir_mechanic/
java -Djava.rmi.server.codebase="file:///Users/giselapinto/Desktop/4°ano/SD/Pratica/Trabalho3/DistributedSystemsRMI/Project/Mechanic/src/dir_mechanic/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     MainPackage.MainProgram &
cd ../../..
sleep 3
echo -e "\n${bold}->${normal} A executar Manager"
cd Manager/src/dir_manager/
java -Djava.rmi.server.codebase="file:///Users/giselapinto/Desktop/4°ano/SD/Pratica/Trabalho3/DistributedSystemsRMI/Project/Manager/src/dir_manager/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     MainPackage.MainProgram &
cd ../../..
sleep 4
echo -e "\n${bold}->${normal} A execução terminou!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
