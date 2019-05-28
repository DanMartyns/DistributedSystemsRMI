bold=$(tput bold)
normal=$(tput sgr0)
echo "\n${bold}*** Script de Recolha do Log ***${normal}"

export SSHPASS='enterro2019'

###

echo -e "\n${bold}* Recolha do log no nó *${normal}"

echo -e "\n${bold}->${normal} A recolher o ficheiro de log da máquina ${bold}1${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws01.ua.pt << EOF
	cd Public/registry/classes
	get -r printRegister.txt Results/printRegister.txt
	cd ../..
	cd logger/classes/
	get -r printLogger.txt Results/printLogger.txt
	get -r log.txt Results/log.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printLounge para a máquina ${bold}2${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws02.ua.pt << EOF
	cd Public/lounge/classes/
	get -r printLounge.txt Results/printLounge.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printRepairArea para a máquina ${bold}3${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws03.ua.pt << EOF
	cd Public/repairArea/classes/
	get -r printRepairArea.txt Results/printRepairArea.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printoutside para a máquina ${bold}4${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws04.ua.pt << EOF
	cd Public/outsideworld/classes/
	get -r printoutside.txt Results/printoutside.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printpark para a máquina ${bold}5${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws05.ua.pt << EOF
	cd Public/park/classes/
	get -r printpark.txt Results/printpark.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printsupplier para a máquina ${bold}6${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws06.ua.pt << EOF
	cd Public/suppliersite/classes/
	get -r printsupplier.txt Results/printsupplier.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printManager para a máquina ${bold}7${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws07.ua.pt << EOF
	cd Public/manager/classes/
	get -r printManager.txt Results/printManager.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printMechanic para a máquina ${bold}8${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws08.ua.pt << EOF
	cd Public/mechanic/classes/
	get -r printMechanic.txt Results/printMechanic.txt
	bye
EOF

echo -e "\n${bold}->${normal} A recolher ficheiro printCustomer para a máquina ${bold}9${normal}"
sshpass -e sftp -o StrictHostKeyChecking=no sd0401@l040101-ws09.ua.pt << EOF
	cd Public/customer/classes/
	get -r printCustomer.txt Results/printCustomer.txt
	bye
EOF
